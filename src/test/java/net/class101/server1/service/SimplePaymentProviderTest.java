package net.class101.server1.service;

import net.class101.server1.PackageItemEntity;
import net.class101.server1.Storage;
import net.class101.server1.domain.*;
import net.class101.server1.repository.InMemoryOrderRepository;
import net.class101.server1.repository.OrderRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class SimplePaymentProviderTest {

    private final OrderRepository repository = new InMemoryOrderRepository();
    private final SimplePaymentProvider sut = new SimplePaymentProvider(repository);

    @Test
    public void sut_is_implemented_PaymentProvider() {
        assertThat(sut).isInstanceOf(PaymentProvider.class);
    }

    @Test
    public void getPayments_will_return_Payment_correctly() {
        List<Order> orders = Arrays.asList(
                new PackageItemOrder(new KitPackageItem(39712, "집에서 느끼는 겨울의 묵직한 포근함, 플랜테리어 아이템", 17600, 8), 1),
                new PackageItemOrder(new KitPackageItem(39712, "집에서 느끼는 겨울의 묵직한 포근함, 플랜테리어 아이템", 17600, 8), 1)
        );
        repository.saveAll(orders);

        List<UUID> orderIds = orders.stream().map(Order::getId).collect(Collectors.toList());
        Payment actual = sut.getPayment(orderIds);

        assertThat(actual.getOrders().size()).isEqualTo(2);
        assertThat(actual.getOrderPrice()).isEqualTo(35200);
        assertThat(actual.getShippingPrice()).isEqualTo(5000);
        assertThat(actual.getPaymentPrice()).isEqualTo(40200);
    }
}