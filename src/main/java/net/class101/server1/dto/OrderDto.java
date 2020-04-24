package net.class101.server1.dto;

public class OrderDto {
    private final long packageItemNumber;
    private final int stock;

    public OrderDto(long packageItemNumber, int stock) {
        this.packageItemNumber = packageItemNumber;
        this.stock = stock;
    }

    public long getPackageItemNumber() {
        return packageItemNumber;
    }

    public int getStock() {
        return stock;
    }
}
