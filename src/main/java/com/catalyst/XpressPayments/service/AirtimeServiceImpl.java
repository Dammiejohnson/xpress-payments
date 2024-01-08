package com.catalyst.XpressPayments.service;

import com.catalyst.XpressPayments.model.Airtime;
import com.catalyst.XpressPayments.repository.AirtimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AirtimeServiceImpl implements AirtimeService{

    private final AirtimeRepository airtimeRepository;

    @Override
    public Optional<Airtime> findByRequestId(String requestId) {
        return airtimeRepository.findByRequestId(requestId);
    }
}
