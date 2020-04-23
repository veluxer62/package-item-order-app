package net.class101.server1;

import java.util.List;

public interface PackageItemRepository {
    List<PackageItem> findAll();
    void save(PackageItem packageItem);
}
