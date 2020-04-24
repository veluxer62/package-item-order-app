package net.class101.server1.service;

import net.class101.server1.domain.PackageItem;

import java.util.List;

public interface PackageItemProvider {
    List<PackageItem> getPackageItems();
}
