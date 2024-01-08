package com.catalyst.XpressPayments.service;

import com.catalyst.XpressPayments.exception.NotFoundException;
import com.catalyst.XpressPayments.model.Airtime;

import java.util.Optional;

public interface AirtimeService {
    Optional<Airtime> findByRequestId(String requestId) throws NotFoundException;
}
