package net.class101.server1.service;

public interface OrderProcessManager<T, F> {
    F order(T message);
}
