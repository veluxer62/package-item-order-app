package net.class101.server1.domain;

import java.util.List;

public class PackageItemPayment implements Payment {

    private final List<Order> orders;

    public PackageItemPayment(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public int getOrderPrice() {
        return orders.stream()
                .map(Order::getPrice)
                .reduce(0, Integer::sum);
    }

    @Override
    public int getShippingPrice() {
        return getOrderPrice() < 50000 ? 5000 : 0;
    }

    @Override
    public int getPaymentPrice() {
        return getOrderPrice() + getShippingPrice();
    }
}
