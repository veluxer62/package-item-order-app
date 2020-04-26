package net.class101.server1.repository;

import net.class101.server1.domain.PackageItem;

import java.util.List;
import java.util.stream.Collectors;

public class InMemoryPackageItemRepository implements PackageItemRepository {
    @Override
    public List<PackageItem> findAll() {
        return Storage.packageItems.stream()
                .map(PackageItemEntity::toPackageItem)
                .collect(Collectors.toList());
    }

    @Override
    public void updateAll(List<PackageItem> packageItems) {
        packageItems.forEach(packageItem -> {
            int idx = Storage.packageItems.stream()
                    .map(PackageItemEntity::getNumber)
                    .collect(Collectors.toList())
                    .indexOf(packageItem.getNumber());

            Storage.packageItems.set(idx, PackageItemEntity.of(packageItem));
        });
    }

    @Override
    public List<PackageItem> findByIdIn(List<Long> ids) {
        return Storage.packageItems.stream()
                .filter(it -> ids.contains(it.getNumber()))
                .map(PackageItemEntity::toPackageItem)
                .collect(Collectors.toList());
    }
}
