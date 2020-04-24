package net.class101.server1.domain;

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

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public long getNumber() {
        return number;
    }

    @Override
    public boolean stockAvailable() {
        return stock > 0;
    }
}
