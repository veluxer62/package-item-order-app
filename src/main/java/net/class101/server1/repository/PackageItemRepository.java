package net.class101.server1.repository;

import net.class101.server1.domain.PackageItem;

import java.util.List;
import java.util.Optional;

public interface PackageItemRepository {
    List<PackageItem> findAll();
    void updateStock(PackageItem packageItem) throws ClassNotFoundException;
    Optional<PackageItem> findById(long id);
}
