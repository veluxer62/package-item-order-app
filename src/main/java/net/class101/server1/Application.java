package net.class101.server1;

import net.class101.server1.controller.PackageItemController;
import net.class101.server1.controller.SimplePackageItemController;
import net.class101.server1.dto.OrderDto;
import net.class101.server1.repository.InMemoryOrderRepository;
import net.class101.server1.repository.InMemoryPackageItemRepository;
import net.class101.server1.repository.OrderRepository;
import net.class101.server1.repository.PackageItemRepository;
import net.class101.server1.resolver.ConsoleViewResolver;
import net.class101.server1.resolver.ViewResolver;
import net.class101.server1.service.*;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Application {
    public static void main(String[] args) {

        ConsoleCommandProcessor processor = initialize();

        while (processor.isRunning()) {

            switch (processor.getMode()) {
                case WELCOME:
                    processor.welcome();
                    break;

                case SELECT:
                    processor.select();
                    break;

                case ORDER:
                    processor.order();
                    break;
            }

        }

    }

    private static ConsoleCommandProcessor initialize() {
        final PackageItemRepository packageItemRepository = new InMemoryPackageItemRepository();
        final OrderRepository orderRepository = new InMemoryOrderRepository();
        final PackageItemProvider provider = new SimplePackageItemProvider(packageItemRepository);
        final OrderProcessManager<List<OrderDto>, List<UUID>> orderProcessManager = new PackageItemOrderProcessManager(packageItemRepository, orderRepository);
        final PaymentProvider paymentProvider = new SimplePaymentProvider(orderRepository);

        final PackageItemController<OrderDto> controller = new SimplePackageItemController(provider, orderProcessManager, paymentProvider);

        final ViewResolver viewResolver = new ConsoleViewResolver();

        Scanner scanner = new Scanner(System.in);

        return new ConsoleCommandProcessor(scanner, controller, viewResolver);
    }
}
