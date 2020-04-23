package net.class101.server1;

import java.util.List;

public interface PackageItemController {
    List<PackageItem> getPackageItems();
    void order(List<Order> orders);
}
