package com.catalyst.XpressPayments.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PurchaseAirtimeServiceImplTest {

    private String xpressPrivateKey = "9v7pZhO4o3VKWreYDReTuDF1LHQ47sdW_CVASPRV";

    @Test
    void testCalculateHMAC512() {
        String purchaseAirtimeObject = "{\"uniqueCode\":\"MTN_24207\",\"requestId\":\"123456\",\"details\":{\"amount\":100,\"phoneNumber\":\"08134740814\"}}";;
        String result = PurchaseAirtimeServiceImpl.calculateHMAC512(purchaseAirtimeObject, xpressPrivateKey);
        assertNotNull(result);
    }

}