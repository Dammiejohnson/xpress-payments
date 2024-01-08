package com.catalyst.XpressPayments.service;

import com.catalyst.XpressPayments.dto.CustomerDetail;
import com.catalyst.XpressPayments.dto.PurchaseAirtimeRequest;
import com.catalyst.XpressPayments.dto.PurchaseAirtimeResponse;
import com.catalyst.XpressPayments.model.Airtime;
import com.catalyst.XpressPayments.model.User;
import com.catalyst.XpressPayments.repository.AirtimeRepository;
import com.catalyst.XpressPayments.repository.UserRepository;
import com.squareup.okhttp.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import static com.catalyst.XpressPayments.utils.Helpers.*;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseAirtimeServiceImpl implements PurchaseAirtimeService {


    private static String PUBLIC_KEY;

    private static String PRIVATE_KEY;

    private final AirtimeRepository airtimeRepository;
    private final UserRepository userRepository;

    @Value("${PUBLIC_KEY}")
    private void setPublicKey(String publicKey) {
        PUBLIC_KEY = publicKey;
    }

    @Value("${PRIVATE_KEY}")
    private void setPrivateKey(String privateKey) {
        PRIVATE_KEY = privateKey;
    }

    @Override
    public PurchaseAirtimeResponse purchaseAirtime(PurchaseAirtimeRequest purchaseAirtimeRequest) throws IOException {
        validatePurchaseRequest(purchaseAirtimeRequest);
        return getPurchaseAirtimeResponse(purchaseAirtimeRequest);
    }

    private PurchaseAirtimeResponse getPurchaseAirtimeResponse(PurchaseAirtimeRequest purchaseAirtimeRequest) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject jsonPurchaseAirtimeRequest = getJsonPurchaseAirtimeRequest(purchaseAirtimeRequest);
        RequestBody requestBody = RequestBody.create(mediaType, jsonPurchaseAirtimeRequest.toString());
        String PaymentHash = calculateHMAC512(jsonPurchaseAirtimeRequest.toString(), PRIVATE_KEY);

        Request request = new Request.Builder()
                .url(AIRTIME_VTU_URL)
                .post(requestBody)
                .addHeader(CONTENT_TYPE, "application/json")
                .addHeader(AUTHORIZATION, BEARER + PUBLIC_KEY)
                .addHeader(PAYMENT_HASH, PaymentHash)
                .addHeader(CHANNEL, "API")
                .build();

        Response response = client.newCall(request).execute();

        String responseBody = response.body().string();
        JSONObject responseBodyAsJson = new JSONObject(responseBody);

        if (!response.isSuccessful()) {
            throw new IOException(responseBodyAsJson.getString("responseMessage"));
        }

        String dataValue = responseBodyAsJson.get("data").toString();

        CustomerDetail airtimeDetails = mapDataToAirtimeDetails(dataValue);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        Optional<User> loggedInUser = userRepository.findByEmail(email);

        Airtime airtime = Airtime.builder()
                .user(loggedInUser.get())
                .requestId(responseBodyAsJson.getString("requestId"))
                .responseCode(responseBodyAsJson.getString("responseCode"))
                .responseMessage(responseBodyAsJson.getString("responseMessage"))
                .build();
        airtimeRepository.save(airtime);

        return PurchaseAirtimeResponse.builder()
                .referenceId(responseBodyAsJson.getString("referenceId"))
                .requestId(responseBodyAsJson.getString("requestId"))
                .responseCode(responseBodyAsJson.getString("responseCode"))
                .responseMessage(responseBodyAsJson.getString("responseMessage"))
                .data(airtimeDetails)
                .build();
    }

    private CustomerDetail mapDataToAirtimeDetails(String dataValue) {
        JSONObject airtimeDetailsJson = new JSONObject(dataValue);
        return CustomerDetail.builder()
                .amount(airtimeDetailsJson.getInt("amount"))
                .phoneNumber(airtimeDetailsJson.getString("phoneNumber"))
                .build();
    }

    private static JSONObject getJsonPurchaseAirtimeRequest(PurchaseAirtimeRequest purchaseAirtimeRequest) {
        JSONObject details = new JSONObject();
        details.put("phoneNumber", purchaseAirtimeRequest.getDetails().getPhoneNumber());
        details.put("amount", purchaseAirtimeRequest.getDetails().getAmount());

        JSONObject jsonPurchaseAirtimeRequest = new JSONObject();
        jsonPurchaseAirtimeRequest.put("requestId", purchaseAirtimeRequest.getRequestId());
        jsonPurchaseAirtimeRequest.put("uniqueCode", purchaseAirtimeRequest.getUniqueCode());
        jsonPurchaseAirtimeRequest.put("details", details);
        return jsonPurchaseAirtimeRequest;
    }

    private void validatePurchaseRequest(PurchaseAirtimeRequest purchaseAirtimeRequest) {
        if (purchaseAirtimeRequest.getRequestId() == null || purchaseAirtimeRequest.getRequestId().isEmpty()) {
            throw new IllegalArgumentException("Invalid requestId");
        }
        if (purchaseAirtimeRequest.getUniqueCode() == null || purchaseAirtimeRequest.getUniqueCode().isEmpty()) {
            throw new IllegalArgumentException("Invalid uniqueCode");
        }
        if (purchaseAirtimeRequest.getDetails() == null) {
            throw new IllegalArgumentException("Details cannot be null");
        }
        if (purchaseAirtimeRequest.getDetails().getPhoneNumber() == null ||
                !isValidPhoneNumber(purchaseAirtimeRequest.getDetails().getPhoneNumber())) {
            throw new IllegalArgumentException("Invalid phoneNumber");
        }
        if (purchaseAirtimeRequest.getDetails().getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d{10,}");
    }

    public static String calculateHMAC512(String data, String key) {
        String HMAC_SHA512 = "HmacSHA512";
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMAC_SHA512);
        Mac mac = null;
        try {
            mac = Mac.getInstance(HMAC_SHA512);
            mac.init(secretKeySpec);
            return Hex.encodeHexString(mac.doFinal(data.getBytes()));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}
