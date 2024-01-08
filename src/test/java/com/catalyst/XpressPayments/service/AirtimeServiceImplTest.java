package com.catalyst.XpressPayments.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AirtimeServiceImplTest {

    private static String PRIVATE_KEY;

    @Value("${PRIVATE_KEY}")
    private void setPrivateKey(String privateKey) {
        PRIVATE_KEY = privateKey;
    }

    @Test
    void testCalculateHMAC512() {
        String purchaseAirtimeObject = "{\"uniqueCode\":\"MTN_24207\",\"requestId\":\"1236\",\"details\":{\"amount\":100,\"phoneNumber\":\"08134740814\"}}";;
        String result = AirtimeServiceImpl.calculateHMAC512(purchaseAirtimeObject, PRIVATE_KEY);
        assertNotNull(result);
    }

}