package com.catalyst.XpressPayments.service;

import com.catalyst.XpressPayments.dto.AirtimeStatusDto;
import com.catalyst.XpressPayments.dto.PurchaseAirtimeRequest;
import com.catalyst.XpressPayments.dto.PurchaseAirtimeResponse;
import com.catalyst.XpressPayments.exception.InvalidRequestException;
import com.catalyst.XpressPayments.exception.NotFoundException;
import com.catalyst.XpressPayments.model.Airtime;

import java.io.IOException;
import java.util.Optional;

public interface AirtimeService {

    AirtimeStatusDto findByRequestId(String requestId) throws NotFoundException;

    PurchaseAirtimeResponse purchaseAirtime(PurchaseAirtimeRequest request) throws IOException, InvalidRequestException;
}
