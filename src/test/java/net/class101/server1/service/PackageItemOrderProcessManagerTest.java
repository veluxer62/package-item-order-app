package net.class101.server1.service;

import net.class101.server1.domain.ClassPackageItem;
import net.class101.server1.domain.Order;
import net.class101.server1.domain.PackageItem;
import net.class101.server1.domain.PackageItemOrder;
import net.class101.server1.dto.OrderDto;
import net.class101.server1.repository.OrderRepository;
import net.class101.server1.repository.PackageItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PackageItemOrderProcessManagerTest {

    private final PackageItemRepository packageItemRepository = Mockito.mock(PackageItemRepository.class);
    private final OrderRepository orderRepository = Mockito.mock(OrderRepository.class);

    private final OrderProcessManager<List<OrderDto>, List<UUID>> sut = new PackageItemOrderProcessManager(packageItemRepository, orderRepository);

    @Test
    public void sut_is_implemented_OrderProcessManager() {
        assertThat(sut).isInstanceOf(OrderProcessManager.class);
    }

    @Test
    public void order_will_return_result_correctly_if_given_message() {
        List<OrderDto> message = Collections.singletonList(new OrderDto(16374, 1));

        ClassPackageItem packageItem = new ClassPackageItem(16374, "스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드 는 비법", 151950);
        Mockito.when(packageItemRepository.findByIdIn(Collections.singletonList(16374L)))
                .thenReturn(Collections.singletonList(packageItem));

        List<UUID> orderIds = sut.order(message);

        assertThat(orderIds.size()).isEqualTo(1);

        List<PackageItem> expectedPackageItems = Collections.singletonList(packageItem);
        Mockito.verify(packageItemRepository).updateAll(expectedPackageItems);

        List<Order> expectedOrders = Collections.singletonList(new PackageItemOrder(packageItem, 1));
        Mockito.verify(orderRepository).saveAll(ArgumentMatchers.argThat(new OrderSaveMatcher(expectedOrders)));
    }
}