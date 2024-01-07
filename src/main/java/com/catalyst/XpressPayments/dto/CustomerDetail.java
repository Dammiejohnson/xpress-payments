package com.catalyst.XpressPayments.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetail {
    private String phoneNumber;
    private int amount;
}
