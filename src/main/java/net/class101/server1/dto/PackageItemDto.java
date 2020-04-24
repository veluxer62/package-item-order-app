package net.class101.server1.dto;

import net.class101.server1.domain.ClassPackageItem;
import net.class101.server1.domain.KitPackageItem;
import net.class101.server1.domain.PackageItem;

public class PackageItemDto {
    private final long number;
    private final String type;
    private final String title;
    private final int price;
    private final int stock;

    private PackageItemDto(long number, String type, String title, int price, int stock) {
        this.number = number;
        this.type = type;
        this.title = title;
        this.price = price;
        this.stock = stock;
    }

    public static PackageItemDto of(PackageItem packageItem) {
        PackageItemDto dto;

        if (packageItem instanceof KitPackageItem) {
            KitPackageItem item = (KitPackageItem) packageItem;
            dto = new PackageItemDto(
                    item.getNumber(),
                    "키트",
                    item.getTitle(),
                    item.getPrice(),
                    item.getStock()
            );
        } else {
            ClassPackageItem item = (ClassPackageItem) packageItem;
            dto = new PackageItemDto(
                    item.getNumber(),
                    "키트",
                    item.getTitle(),
                    item.getPrice(),
                    99999
            );
        }

        return dto;
    }

    public long getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

}
