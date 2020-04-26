package net.class101.server1.repository;

import net.class101.server1.domain.Order;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class InMemoryOrderRepository implements OrderRepository {
    @Override
    public List<Order> findByIdIn(List<UUID> orderIds) {
        return Storage.orders.stream()
                .filter(it -> orderIds.contains(it.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void saveAll(List<Order> orders) {
        Storage.orders.addAll(orders);
    }
}
