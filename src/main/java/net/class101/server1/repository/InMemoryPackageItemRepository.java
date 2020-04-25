package net.class101.server1.repository;

import net.class101.server1.Storage;
import net.class101.server1.domain.KitPackageItem;
import net.class101.server1.domain.PackageItem;

import java.util.List;

public class InMemoryPackageItemRepository implements PackageItemRepository {
    @Override
    public List<PackageItem> findAll() {
        return Storage.packageItems;
    }

    @Override
    public void updateStock(PackageItem packageItem) throws ClassNotFoundException {
        if (!(packageItem instanceof KitPackageItem)) {
            throw new IllegalArgumentException();
        }

        PackageItem foundItem = Storage.packageItems.stream()
                .filter(it -> it.getNumber() == packageItem.getNumber())
                .findFirst()
                .orElseThrow(ClassNotFoundException::new);

        ((KitPackageItem) foundItem).setStock(packageItem.getStock());
    }
}
