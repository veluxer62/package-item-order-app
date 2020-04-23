package net.class101.server1;

public class PackageItemDto {
    private final long number;
    private final String title;
    private final int price;
    private final int stock;

    public PackageItemDto(long number, String title, int price, int stock) {
        this.number = number;
        this.title = title;
        this.price = price;
        this.stock = stock;
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