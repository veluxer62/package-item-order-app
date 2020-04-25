package net.class101.server1.repository;

import net.class101.server1.Storage;
import net.class101.server1.domain.PackageItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryPackageItemRepository implements PackageItemRepository {
    @Override
    public List<PackageItem> findAll() {
        return new ArrayList<>(Storage.packageItems);
    }

    @Override
    public void updateAll(List<PackageItem> packageItems) {
        packageItems.forEach(packageItem -> {
            int idx = Storage.packageItems.stream()
                    .map(PackageItem::getNumber)
                    .collect(Collectors.toList())
                    .indexOf(packageItem.getNumber());

            Storage.packageItems.set(idx, packageItem);
        });
    }

    @Override
    public List<PackageItem> findByIdIn(List<Long> ids) {
        return Storage.packageItems.stream()
                .filter(it -> ids.contains(it.getNumber()))
                .collect(Collectors.toList());
    }
}
