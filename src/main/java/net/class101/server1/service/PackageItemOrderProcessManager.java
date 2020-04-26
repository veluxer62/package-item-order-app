package net.class101.server1.service;

import net.class101.server1.IllegalOrderException;
import net.class101.server1.IllegalPaymentException;
import net.class101.server1.domain.ClassPackageItem;
import net.class101.server1.domain.Order;
import net.class101.server1.domain.PackageItem;
import net.class101.server1.domain.PackageItemOrder;
import net.class101.server1.dto.OrderDto;
import net.class101.server1.repository.OrderRepository;
import net.class101.server1.repository.PackageItemRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class PackageItemOrderProcessManager implements OrderProcessManager<List<OrderDto>, List<UUID>> {
    private final PackageItemRepository packageItemRepository;
    private final OrderRepository orderRepository;

    public PackageItemOrderProcessManager(PackageItemRepository packageItemRepository, OrderRepository orderRepository) {
        this.packageItemRepository = packageItemRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<UUID> order(List<OrderDto> message) {
        List<Long> packageItemNumbers = message.stream()
                .map(OrderDto::getPackageItemNumber)
                .collect(Collectors.toList());

        List<PackageItem> packageItems = packageItemRepository.findByIdIn(packageItemNumbers);

        List<Order> orders = message.stream()
                .map(dto -> {
                    PackageItem packageItem = packageItems.stream()
                            .filter(it -> it.getNumber() == dto.getPackageItemNumber())
                            .findFirst()
                            .orElseThrow(IllegalArgumentException::new);

                    int changeStock = packageItem.getStock() - dto.getOrderCount();
                    packageItem.setStock(changeStock);

                    return new PackageItemOrder(packageItem, dto.getOrderCount());
                })
                .collect(Collectors.toList());

        long count = orders.stream()
                .filter(it -> it.getPackageItem() instanceof ClassPackageItem)
                .map(Order::getPackageItem)
                .filter(it -> Collections.frequency(packageItemNumbers, it.getNumber()) > 1)
                .count();

        if (count > 0) {
            throw new IllegalOrderException();
        }

        orderRepository.saveAll(orders);

        packageItemRepository.updateAll(packageItems);

        return orders.stream()
                .map(Order::getId)
                .collect(Collectors.toList());
    }
}
