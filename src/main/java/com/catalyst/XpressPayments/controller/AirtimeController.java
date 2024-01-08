package com.catalyst.XpressPayments.controller;


import com.catalyst.XpressPayments.dto.AirtimeResponse;
import com.catalyst.XpressPayments.dto.PurchaseAirtimeRequest;
import com.catalyst.XpressPayments.exception.InvalidRequestException;
import com.catalyst.XpressPayments.exception.NotFoundException;
import com.catalyst.XpressPayments.model.Airtime;
import com.catalyst.XpressPayments.service.AirtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/airtime")
@RequiredArgsConstructor
public class AirtimeController {

    private final AirtimeService airtimeService;


    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseAirtime(@RequestBody PurchaseAirtimeRequest request) throws IOException, InvalidRequestException {
            AirtimeResponse response = AirtimeResponse.builder()
                    .payload(airtimeService.purchaseAirtime(request))
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/status")
    public ResponseEntity<?> airtimeStatus(@RequestParam String requestId) throws NotFoundException {
            AirtimeResponse response = AirtimeResponse.builder()
                    .payload(airtimeService.findByRequestId(requestId))
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
