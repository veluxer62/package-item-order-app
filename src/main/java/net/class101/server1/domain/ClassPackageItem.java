package net.class101.server1.domain;

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
}
