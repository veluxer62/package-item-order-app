package net.class101.server1;

import net.class101.server1.domain.PackageItem;

import java.util.List;

public interface PackageItemRepository {
    List<PackageItem> findAll();
    void save(PackageItem packageItem);
}
