package net.class101.server1.dto;

public class OrderDto {
    private final long packageItemNumber;
    private final int orderCount;

    public OrderDto(long packageItemNumber, int orderCount) {
        this.packageItemNumber = packageItemNumber;
        this.orderCount = orderCount;
    }

    public long getPackageItemNumber() {
        return packageItemNumber;
    }

    public int getOrderCount() {
        return orderCount;
    }
}
