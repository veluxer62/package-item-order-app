package net.class101.server1.service;

import net.class101.server1.domain.Payment;

import java.util.List;
import java.util.UUID;

public interface PaymentProvider {
    Payment getPayment(List<UUID> ids);
}
