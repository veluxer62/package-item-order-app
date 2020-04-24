package net.class101.server1.service;

public interface OrderProcessManager<P, R> {
    R order(P message);
}
