package com.catalyst.XpressPayments.service;

import com.catalyst.XpressPayments.dto.PurchaseAirtimeRequest;
import com.catalyst.XpressPayments.dto.PurchaseAirtimeResponse;

import java.io.IOException;

public interface PurchaseAirtimeService {

    PurchaseAirtimeResponse purchaseAirtime(PurchaseAirtimeRequest request) throws IOException;
}
