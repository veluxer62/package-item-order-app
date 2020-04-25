package net.class101.server1.service;

public interface OrderProcessManager<T> {
    void order(T message);
}
