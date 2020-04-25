package net.class101.server1.domain;

import java.util.List;

public interface Payment {
    List<Order> getOrders();
    int getOrderPrice();
    int getShippingPrice();
    int getPaymentPrice();
}
