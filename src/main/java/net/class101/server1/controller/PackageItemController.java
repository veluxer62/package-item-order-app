package net.class101.server1.controller;

import net.class101.server1.Order;
import net.class101.server1.dto.Response;

import java.util.List;

public interface PackageItemController {
    Response getPackageItems();
    Response order(List<Order> orders);
}
