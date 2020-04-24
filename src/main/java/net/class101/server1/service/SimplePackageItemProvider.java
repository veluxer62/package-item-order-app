package net.class101.server1.service;

import net.class101.server1.domain.PackageItem;
import net.class101.server1.repository.PackageItemRepository;

import java.util.List;

public class SimplePackageItemProvider implements PackageItemProvider {

    private final PackageItemRepository repository;

    public SimplePackageItemProvider(PackageItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PackageItem> getPackageItems() {
        return repository.findAll();
    }
}
