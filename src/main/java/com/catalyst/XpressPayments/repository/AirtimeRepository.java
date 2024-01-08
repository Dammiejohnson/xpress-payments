package com.catalyst.XpressPayments.repository;

import com.catalyst.XpressPayments.model.Airtime;
import com.catalyst.XpressPayments.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirtimeRepository extends JpaRepository<Airtime, Integer> {

    Optional<Airtime> findByRequestId(String requestId);
}
