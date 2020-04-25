package net.class101.server1.domain;

import java.util.Objects;

public class KitPackageItem implements PackageItem {
    private final long number;
    private final String title;
    private final int price;
    private int stock;

    public KitPackageItem(long number, String title, int price, int stock) {
        this.number = number;
        this.title = title;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public long getNumber() {
        return number;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getStock() {
        return stock;
    }

    @Override
    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KitPackageItem that = (KitPackageItem) o;
        return number == that.number &&
                price == that.price &&
                stock == that.stock &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, title, price, stock);
    }
}
