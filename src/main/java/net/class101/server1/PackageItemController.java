package net.class101.server1;

import java.util.List;

public interface PackageItemController {
    Response getPackageItems();
    Response order(List<Order> orders);
}
