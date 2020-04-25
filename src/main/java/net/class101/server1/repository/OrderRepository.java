package net.class101.server1.repository;

import net.class101.server1.domain.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {
    List<Order> findByIdIn(List<UUID> orderIds);
    void saveAll(List<Order> orders);
}
