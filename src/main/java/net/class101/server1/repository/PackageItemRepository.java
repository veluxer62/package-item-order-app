package net.class101.server1.repository;

import net.class101.server1.domain.PackageItem;

import java.util.List;

public interface PackageItemRepository {
    List<PackageItem> findAll();
    void updateAll(List<PackageItem> packageItems);
    List<PackageItem> findByIdIn(List<Long> ids);
}
