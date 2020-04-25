package net.class101.server1.service;

import net.class101.server1.domain.Order;
import net.class101.server1.domain.PackageItem;
import org.mockito.ArgumentMatcher;

import java.util.List;
import java.util.stream.Collectors;

public class OrderSaveMatcher implements ArgumentMatcher<List<Order>> {

    private final List<Order> target;

    public OrderSaveMatcher(List<Order> target) {
        this.target = target;
    }

    @Override
    public boolean matches(List<Order> argument) {
        return convertComparableStrings(target)
                .containsAll(convertComparableStrings(argument));
    }

    private List<String> convertComparableStrings(List<Order> orders) {
        return orders.stream()
                .map(it -> {
                    PackageItem packageItem = it.getPackageItem();
                    return String.format("%d|%s|%d|%d|%d", packageItem.getNumber(), packageItem.getTitle(), packageItem.getPrice(), packageItem.getStock(), it.getOrderCount());
                })
                .collect(Collectors.toList());
    }
}
