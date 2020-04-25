package net.class101.server1.repository;

import net.class101.server1.Storage;
import net.class101.server1.domain.Order;
import net.class101.server1.domain.PackageItemOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryOrderRepositoryTest {

    private final InMemoryOrderRepository sut = new InMemoryOrderRepository();

    @BeforeEach
    public void beforeEach() {
        Storage.orders.add(new PackageItemOrder(Storage.packageItems.get(0), 1));
        Storage.orders.add(new PackageItemOrder(Storage.packageItems.get(1), 1));
        Storage.orders.add(new PackageItemOrder(Storage.packageItems.get(2), 1));
        Storage.orders.add(new PackageItemOrder(Storage.packageItems.get(3), 1));
    }

    @AfterEach
    public void afterEach() {
        Storage.orders.clear();
    }

    @Test
    public void sut_is_implemented_OrderRepository() {
        assertThat(sut).isInstanceOf(OrderRepository.class);
    }

    @Test
    public void findByIdIn_will_return_order_correctly() {
        UUID id1 = Storage.orders.get(0).getId();
        UUID id2 = Storage.orders.get(1).getId();

        List<Order> actual = sut.findByIdIn(Arrays.asList(id1, id2));

        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    public void save_will_insert_order_correctly() {
        Storage.orders.clear();
        PackageItemOrder order = new PackageItemOrder(Storage.packageItems.get(0), 1);

        sut.save(order);

        assertThat(Storage.orders).isNotEmpty();
    }

}