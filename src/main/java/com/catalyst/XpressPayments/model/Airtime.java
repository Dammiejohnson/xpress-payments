package com.catalyst.XpressPayments.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "airtime")
public class Airtime {
    @Id
    @GeneratedValue
    private Integer id;
    private String requestId;
    private String responseCode;
    private String responseMessage;

    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;


}
