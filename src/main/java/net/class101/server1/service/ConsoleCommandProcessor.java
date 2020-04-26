package net.class101.server1.service;

import net.class101.server1.controller.PackageItemController;
import net.class101.server1.dto.Mode;
import net.class101.server1.dto.OrderDto;
import net.class101.server1.dto.Response;
import net.class101.server1.resolver.ViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleCommandProcessor {
    private final Scanner scanner;
    private final PackageItemController<OrderDto> controller;
    private final ViewResolver viewResolver;

    public ConsoleCommandProcessor(Scanner scanner, PackageItemController<OrderDto> controller, ViewResolver viewResolver) {
        this.scanner = scanner;
        this.controller = controller;
        this.viewResolver = viewResolver;
    }

    private final List<OrderDto> orders = new ArrayList<>();

    private Mode mode = Mode.WELCOME;
    private boolean running = true;

    public Mode getMode() {
        return mode;
    }

    public boolean isRunning() {
        return running;
    }

    public void welcome() {
        System.out.print("입력(o[order]: 주문, q[quit]: 종료 : ");
        String init = scanner.nextLine();
        controller.getPackageItems();
        System.out.println();

        if (init.equals("o")) {
            mode = Mode.SELECT;
        } else {
            running = false;
        }
    }

    public void select() {
        System.out.print("상품번호 : ");
        String packageNumber = scanner.nextLine();

        if (packageNumber.trim().isEmpty()) {
            mode = Mode.ORDER;
        } else {
            System.out.print("수량 : ");
            String orderCount = scanner.nextLine();

            orders.add(new OrderDto(Long.parseLong(packageNumber), Integer.parseInt(orderCount)));
        }
    }

    public void order() {
        Response orderResult = controller.order(orders);
        viewResolver.show(orderResult);
        orders.clear();
        mode = Mode.WELCOME;
    }
}
