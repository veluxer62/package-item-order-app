package net.class101.server1.service;

import net.class101.server1.domain.Order;
import net.class101.server1.domain.PackageItem;
import net.class101.server1.domain.PackageItemOrder;
import net.class101.server1.dto.OrderDto;
import net.class101.server1.repository.OrderRepository;
import net.class101.server1.repository.PackageItemRepository;

import java.util.List;
import java.util.stream.Collectors;

public class PackageItemOrderProcessManager implements OrderProcessManager<List<OrderDto>> {
    private final PackageItemRepository packageItemRepository;
    private final OrderRepository orderRepository;

    public PackageItemOrderProcessManager(PackageItemRepository packageItemRepository, OrderRepository orderRepository) {
        this.packageItemRepository = packageItemRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void order(List<OrderDto> message) {
        List<Long> packageItemNumbers = message.stream()
                .map(OrderDto::getPackageItemNumber)
                .collect(Collectors.toList());

        List<PackageItem> packageItems = packageItemRepository.findByIdIn(packageItemNumbers);

        List<Order> orders = packageItems.stream()
                .map(it -> {
                    OrderDto orderDto = message.stream()
                            .filter(msg -> msg.getPackageItemNumber() == it.getNumber())
                            .findFirst()
                            .orElseThrow(IllegalAccessError::new);

                    return new PackageItemOrder(it, orderDto.getOrderCount());
                })
                .collect(Collectors.toList());

        message.forEach(dto -> {
            PackageItem packageItem = packageItems.stream()
                    .filter(it -> it.getNumber() == dto.getPackageItemNumber())
                    .findFirst()
                    .orElseThrow(IllegalAccessError::new);
            int changeStock = packageItem.getStock() - dto.getOrderCount();
            packageItem.setStock(changeStock);
        });

        packageItemRepository.updateAll(packageItems);

        orderRepository.saveAll(orders);
    }
}
