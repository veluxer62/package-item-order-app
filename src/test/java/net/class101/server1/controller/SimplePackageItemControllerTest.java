package net.class101.server1.controller;

import net.class101.server1.SoldOutException;
import net.class101.server1.StorageRecoveryHelper;
import net.class101.server1.dto.OrderDto;
import net.class101.server1.dto.PackageItemsDto;
import net.class101.server1.dto.PaymentDto;
import net.class101.server1.dto.Response;
import net.class101.server1.repository.InMemoryOrderRepository;
import net.class101.server1.repository.InMemoryPackageItemRepository;
import net.class101.server1.repository.OrderRepository;
import net.class101.server1.repository.PackageItemRepository;
import net.class101.server1.service.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;

class SimplePackageItemControllerTest {

    private final PackageItemRepository packageItemRepository = new InMemoryPackageItemRepository();
    private final OrderRepository orderRepository = new InMemoryOrderRepository();
    private final PackageItemProvider provider = new SimplePackageItemProvider(packageItemRepository);
    private final OrderProcessManager<List<OrderDto>, List<UUID>> orderProcessManager = new PackageItemOrderProcessManager(packageItemRepository, orderRepository);
    private final PaymentProvider paymentProvider = new SimplePaymentProvider(orderRepository);

    private final SimplePackageItemController sut = new SimplePackageItemController(provider, orderProcessManager, paymentProvider);

    @AfterEach
    public void afterEach() {
        StorageRecoveryHelper.recovery();
    }

    @Test
    public void sut_is_implemented_PackageItemController() {
        assertThat(sut).isInstanceOf(PackageItemController.class);
    }

    @Test
    public void getPackageItems_will_return_PackageItem_list_correctly() {
        Response actual = sut.getPackageItems();
        assertThat(actual).isInstanceOf(PackageItemsDto.class);
    }

    @Test
    public void order_will_return_Response_correctly() {
        List<OrderDto> message = Arrays.asList(
                new OrderDto(16374, 1),
                new OrderDto(91008, 3),
                new OrderDto(91008, 2)
        );

        Response actual = sut.order(message);

        assertThat(actual).isInstanceOf(PaymentDto.class);
    }

    @Test
    public void testCounterWithConcurrency() throws InterruptedException {
        int numberOfThreads = 5;
        ExecutorService service = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        List<Future<?>> futureList = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            Future<?> submit = service.submit(() -> {
                try {
                    List<OrderDto> message = Arrays.asList(
                            new OrderDto(16374, 1),
                            new OrderDto(91008, 3)
                    );
                    sut.order(message);
                } finally {
                    latch.countDown();
                }
            });

            futureList.add(submit);
        }
        latch.await(10, TimeUnit.SECONDS);

        long expected = futureList.stream()
                .map(it -> {
                    try {
                        it.get();
                        return new Throwable();
                    } catch (InterruptedException | ExecutionException e) {
                        return e.getCause();
                    }
                })
                .filter(it -> it instanceof SoldOutException)
                .count();

        assertThat(expected).isEqualTo(2);
    }

}