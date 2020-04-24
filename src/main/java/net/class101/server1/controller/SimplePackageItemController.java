package net.class101.server1.controller;

import net.class101.server1.Order;
import net.class101.server1.dto.PackageItemsDto;
import net.class101.server1.dto.Response;
import net.class101.server1.service.PackageItemProvider;

import java.util.List;

public class SimplePackageItemController implements PackageItemController {
    private final PackageItemProvider provider;

    public SimplePackageItemController(PackageItemProvider provider) {
        this.provider = provider;
    }

    @Override
    public Response getPackageItems() {
        return PackageItemsDto.of(provider.getPackageItems());
    }

    @Override
    public Response order(List<Order> orders) {
        return null;
    }
}
