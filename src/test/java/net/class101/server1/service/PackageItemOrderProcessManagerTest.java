package net.class101.server1.service;

import net.class101.server1.*;
import net.class101.server1.domain.PackageItem;
import net.class101.server1.dto.OrderDto;
import net.class101.server1.repository.InMemoryOrderRepository;
import net.class101.server1.repository.InMemoryPackageItemRepository;
import net.class101.server1.repository.OrderRepository;
import net.class101.server1.repository.PackageItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PackageItemOrderProcessManagerTest {

    private final PackageItemRepository packageItemRepository = new InMemoryPackageItemRepository();
    private final OrderRepository orderRepository = new InMemoryOrderRepository();
    private final OrderProcessManager<List<OrderDto>, List<UUID>> sut = new PackageItemOrderProcessManager(packageItemRepository, orderRepository);

    @AfterEach
    public void afterEach() {
        StorageRecoveryHelper.recovery();
    }

    @Test
    public void sut_is_implemented_OrderProcessManager() {
        assertThat(sut).isInstanceOf(OrderProcessManager.class);
    }

    @Test
    public void order_will_return_result_correctly_if_given_message() {
        List<OrderDto> message = Arrays.asList(
                new OrderDto(16374, 1),
                new OrderDto(91008, 3),
                new OrderDto(91008, 2)
        );

        List<UUID> orderIds = sut.order(message);

        assertThat(orderIds.size()).isEqualTo(3);

        assertThat(Storage.orders.size()).isEqualTo(3);

        Optional<PackageItemEntity> expectedEntity = Storage.packageItems.stream()
                .filter(it -> it.getNumber() == 91008)
                .findFirst();
        assertThat(expectedEntity).isNotEmpty();
        assertThat(expectedEntity.get().getStock()).isEqualTo(5);
    }

    @Test
    public void order_will_throw_SoldOutException_if_given_order_has_over_orderCount() {
        List<OrderDto> message = Arrays.asList(
                new OrderDto(16374, 1),
                new OrderDto(91008, 3),
                new OrderDto(91008, 5),
                new OrderDto(91008, 3)
        );

        Assertions.assertThrows(SoldOutException.class, () -> sut.order(message));

        assertThat(Storage.orders.size()).isEqualTo(0);

        Optional<PackageItemEntity> expectedEntity = Storage.packageItems.stream()
                .filter(it -> it.getNumber() == 91008)
                .findFirst();
        assertThat(expectedEntity).isNotEmpty();
        assertThat(expectedEntity.get().getStock()).isEqualTo(10);
    }

    @Test
    public void order_will_throw_IllegalOrderException_if_given_order_has_duplicated_class_package() {
        List<OrderDto> message = Arrays.asList(
                new OrderDto(16374, 1),
                new OrderDto(16374, 1),
                new OrderDto(91008, 3)
        );

        Assertions.assertThrows(IllegalOrderException.class, () -> sut.order(message));

        assertThat(Storage.orders.size()).isEqualTo(0);

        Optional<PackageItemEntity> expectedEntity = Storage.packageItems.stream()
                .filter(it -> it.getNumber() == 91008)
                .findFirst();
        assertThat(expectedEntity).isNotEmpty();
        assertThat(expectedEntity.get().getStock()).isEqualTo(10);
    }
}