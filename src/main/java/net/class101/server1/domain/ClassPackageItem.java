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

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public long getNumber() {
        return number;
    }

    @Override
    public boolean stockAvailable() {
        return true;
    }
}
