package com.catalyst.XpressPayments.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class PurchaseAirtimeRequest {
    private String requestId;
    private String uniqueCode;
    private CustomerDetail details;
}
