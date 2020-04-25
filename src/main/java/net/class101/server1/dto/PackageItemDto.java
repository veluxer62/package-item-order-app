package net.class101.server1.dto;

import net.class101.server1.domain.PackageItem;

public class PackageItemDto {
    private final long number;
    private final String title;
    private final int price;
    private final int stock;

    private PackageItemDto(long number, String title, int price, int stock) {
        this.number = number;
        this.title = title;
        this.price = price;
        this.stock = stock;
    }

    public static PackageItemDto of(PackageItem packageItem) {
        return new PackageItemDto(
                packageItem.getNumber(),
                packageItem.getTitle(),
                packageItem.getPrice(),
                packageItem.getStock()
        );
    }

    public long getNumber() {
        return number;
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
