package net.class101.server1.controller;

import net.class101.server1.domain.Payment;
import net.class101.server1.dto.OrderDto;
import net.class101.server1.dto.PackageItemsDto;
import net.class101.server1.dto.PaymentDto;
import net.class101.server1.dto.Response;
import net.class101.server1.exception.IllegalOrderException;
import net.class101.server1.service.OrderProcessManager;
import net.class101.server1.service.PackageItemProvider;
import net.class101.server1.service.PaymentProvider;

import java.util.List;
import java.util.UUID;

public class SimplePackageItemController implements PackageItemController<OrderDto> {
    private final PackageItemProvider packageItemProvider;
    private final OrderProcessManager<List<OrderDto>, List<UUID>> orderProcessManager;
    private final PaymentProvider paymentProvider;

    public SimplePackageItemController(
            PackageItemProvider packageItemProvider,
            OrderProcessManager<List<OrderDto>, List<UUID>> orderProcessManager,
            PaymentProvider paymentProvider) {
        this.packageItemProvider = packageItemProvider;
        this.orderProcessManager = orderProcessManager;
        this.paymentProvider = paymentProvider;
    }

    @Override
    public Response getPackageItems() {
        return PackageItemsDto.of(packageItemProvider.getPackageItems());
    }

    @Override
    public synchronized Response order(List<OrderDto> orders) {
        if (orders.isEmpty()) {
            throw new IllegalOrderException("반드시 하나 이상의 주문을 입력해 주세요.");
        }

        List<UUID> orderIds = orderProcessManager.order(orders);
        Payment payment = paymentProvider.getPayment(orderIds);
        return new PaymentDto(payment);
    }
}
