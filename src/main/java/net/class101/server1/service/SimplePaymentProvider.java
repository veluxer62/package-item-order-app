package net.class101.server1.service;

import net.class101.server1.domain.Order;
import net.class101.server1.domain.PackageItemPayment;
import net.class101.server1.domain.Payment;
import net.class101.server1.repository.OrderRepository;

import java.util.List;
import java.util.UUID;

public class SimplePaymentProvider implements PaymentProvider {
    private final OrderRepository repository;

    public SimplePaymentProvider(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payment getPayment(List<UUID> ids) {
        List<Order> orders = repository.findByIdIn(ids);
        return new PackageItemPayment(orders);
    }
}
