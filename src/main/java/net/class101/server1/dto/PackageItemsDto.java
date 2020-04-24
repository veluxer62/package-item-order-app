package net.class101.server1.dto;

import net.class101.server1.domain.PackageItem;

import java.util.List;
import java.util.stream.Collectors;

public class PackageItemsDto implements Response {
    private final List<PackageItemDto> items;

    private PackageItemsDto(List<PackageItemDto> items) {
        this.items = items;
    }

    public static PackageItemsDto of(List<PackageItem> packageItemList) {
        return new PackageItemsDto(
                packageItemList.stream()
                        .map(PackageItemDto::of)
                        .collect(Collectors.toList())
        );
    }

    public List<PackageItemDto> getItems() {
        return items;
    }
}
