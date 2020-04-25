package net.class101.server1.domain;

public interface PackageItem {
    long getNumber();
    String getTitle();
    int getPrice();
    int getStock();
    void setStock(int stock);
}
