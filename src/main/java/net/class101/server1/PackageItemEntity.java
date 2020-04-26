package net.class101.server1;

import net.class101.server1.domain.ClassPackageItem;
import net.class101.server1.domain.KitPackageItem;
import net.class101.server1.domain.PackageItem;

public class PackageItemEntity {
    private final long number;
    private final String type;
    private final String title;
    private final int price;
    private final int stock;

    public PackageItemEntity(long number, String type, String title, int price, int stock) {
        this.number = number;
        this.type = type;
        this.title = title;
        this.price = price;
        this.stock = stock;
    }

    public static PackageItemEntity of(PackageItem item) {
        PackageItemEntity entity;

        if (item instanceof ClassPackageItem) {
            entity = new PackageItemEntity(item.getNumber(), "클래스", item.getTitle(), item.getPrice(), item.getStock());
        } else {
            entity = new PackageItemEntity(item.getNumber(), "키트", item.getTitle(), item.getPrice(), item.getStock());
        }

        return entity;
    }

    public PackageItem toPackageItem() {
        PackageItem packageItem;
        if (type.equals("클래스")) {
            packageItem = new ClassPackageItem(number, title, price);
        } else {
            packageItem = new KitPackageItem(number, title, price, stock);
        }
        return packageItem;
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
