package net.class101.server1.domain;

import java.util.Objects;

public class ClassPackageItem implements PackageItem {

    private final long number;
    private final String title;
    private final int price;

    public ClassPackageItem(long number, String title, int price) {
        this.number = number;
        this.title = title;
        this.price = price;
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
        return 99999;
    }

    @Override
    public void setStock(int stock) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassPackageItem that = (ClassPackageItem) o;
        return number == that.number &&
                price == that.price &&
                title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, title, price);
    }
}
