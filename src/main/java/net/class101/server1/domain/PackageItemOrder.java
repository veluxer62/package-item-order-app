package net.class101.server1.domain;

import net.class101.server1.IllegalOrderCountException;
import net.class101.server1.SoldOutException;

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
            throw new IllegalOrderCountException();
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
}
