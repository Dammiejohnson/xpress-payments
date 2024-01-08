package com.catalyst.XpressPayments.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AirtimeStatusDto {
    private Integer id;
    private String requestId;
    private String userId;
    private String responseCode;
    private String responseMessage;
}
