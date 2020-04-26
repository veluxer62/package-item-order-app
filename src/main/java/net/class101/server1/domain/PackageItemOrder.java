package net.class101.server1.domain;

import net.class101.server1.exception.IllegalOrderException;
import net.class101.server1.exception.SoldOutException;

import java.util.UUID;

public class PackageItemOrder implements Order {
    private final UUID id;
    private final PackageItem packageItem;
    private final int orderCount;

    public PackageItemOrder(PackageItem packageItem, int orderCount) {
        if (packageItem.getStock() < orderCount) {
            throw new SoldOutException();
        }

        if (orderCount <= 0) {
            throw new IllegalOrderException("주문 수량은 1개 이상 이어야 합니다.");
        }

        this.id = UUID.randomUUID();
        this.packageItem = packageItem;
        this.orderCount = orderCount;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public int getPrice() {
        return packageItem.getPrice() * orderCount;
    }

    @Override
    public PackageItem getPackageItem() {
        return packageItem;
    }

    @Override
    public int getOrderCount() {
        return orderCount;
    }
}
