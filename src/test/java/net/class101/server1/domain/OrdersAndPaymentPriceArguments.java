package net.class101.server1.domain;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class OrdersAndPaymentPriceArguments implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                () -> {
                    List<Order> orders = Arrays.asList(
                            new PackageItemOrder(new ClassPackageItem(16374, "스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드 는 비법", 151950), 1),
                            new PackageItemOrder(new KitPackageItem(91008, "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트", 28000, 10), 2)
                    );

                    return new Object[] {orders, 207950};
                },

                () -> {
                    List<Order> orders = Collections.singletonList(
                            new PackageItemOrder(new KitPackageItem(9235, "하루의 시작과 끝, 욕실의 포근함을 선사하는 천연 비누", 9900, 22), 5)
                    );

                    return new Object[] {orders, 54500};
                }
        );
    }
}
