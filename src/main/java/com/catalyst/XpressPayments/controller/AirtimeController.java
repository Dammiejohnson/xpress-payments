package com.catalyst.XpressPayments.controller;


import com.catalyst.XpressPayments.dto.AirtimeResponse;
import com.catalyst.XpressPayments.dto.PurchaseAirtimeRequest;
import com.catalyst.XpressPayments.exception.NotFoundException;
import com.catalyst.XpressPayments.service.AirtimeService;
import com.catalyst.XpressPayments.service.PurchaseAirtimeService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/airtime")
@RequiredArgsConstructor
public class AirtimeController {

    private final PurchaseAirtimeService purchaseAirtimeService;

    private final AirtimeService airtimeService;

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseAirtime(@RequestBody PurchaseAirtimeRequest request) throws IOException {
        try {
            AirtimeResponse response = AirtimeResponse.builder()
                    .payload(purchaseAirtimeService.purchaseAirtime(request))
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT Token Expired");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(" Duplicate request ID, Request already exist");
        }

    }

//    @GetMapping("/status")
//    public ResponseEntity<?> airtimeStatus(@RequestParam String requestId) throws IOException {
//        try {
//            AirtimeResponse response = AirtimeResponse.builder()
//                    .payload(airtimeService.findByRequestId(requestId))
//                    .build();
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        } catch (RuntimeException e) {
//            throw new RuntimeException("Not Found");
//        } catch (NotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }


}
