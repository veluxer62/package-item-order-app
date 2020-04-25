package net.class101.server1.domain;

import net.class101.server1.IllegalPaymentException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PackageItemPayment implements Payment {

    private final List<Order> orders;

    public PackageItemPayment(List<Order> orders) {
        List<Long> orderNumbers = orders.stream()
                .map(it -> it.getPackageItem().getNumber())
                .collect(Collectors.toList());

        long count = orders.stream()
                .filter(it -> it.getPackageItem() instanceof ClassPackageItem)
                .map(Order::getPackageItem)
                .filter(it -> Collections.frequency(orderNumbers, it.getNumber()) > 1)
                .count();

        if (count > 0) {
            throw new IllegalPaymentException();
        }

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
