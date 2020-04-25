package net.class101.server1.domain;

import java.util.UUID;

public interface Order {
    UUID getId();
    int getPrice();
    PackageItem getPackageItem();
    int getOrderCount();
}