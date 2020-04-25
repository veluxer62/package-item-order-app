package net.class101.server1.domain;

import net.class101.server1.IllegalPaymentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PackageItemPaymentTest {

    @Test
    public void sut_is_implemented_Payment() {
        PackageItemPayment sut = new PackageItemPayment(Collections.emptyList());
        assertThat(sut).isInstanceOf(Payment.class);
    }

    @Test
    public void getOrders_will_return_order_correctly() {
        List<Order> orders = Arrays.asList(
                new PackageItemOrder(new ClassPackageItem(16374, "스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드 는 비법", 151950), 1),
                new PackageItemOrder(new KitPackageItem(91008, "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트", 28000, 10), 2)
        );
        PackageItemPayment sut = new PackageItemPayment(orders);

        List<Order> actual = sut.getOrders();
        assertThat(actual).isNotEmpty();
        assertThat(actual).containsAll(orders);
    }

    @Test
    public void getOrderPrice_will_return_orderPrice_correctly() {
        List<Order> orders = Arrays.asList(
                new PackageItemOrder(new ClassPackageItem(16374, "스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드 는 비법", 151950), 1),
                new PackageItemOrder(new KitPackageItem(91008, "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트", 28000, 10), 2)
        );
        PackageItemPayment sut = new PackageItemPayment(orders);

        int actual = sut.getOrderPrice();

        assertThat(actual).isEqualTo(207950);
    }

    @Test
    public void getShippingPrice_will_return_zero_if_given_order_price_is_more_then_50000() {
        List<Order> orders = Arrays.asList(
                new PackageItemOrder(new ClassPackageItem(16374, "스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드 는 비법", 151950), 1),
                new PackageItemOrder(new KitPackageItem(91008, "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트", 28000, 10), 2)
        );
        PackageItemPayment sut = new PackageItemPayment(orders);

        int actual = sut.getShippingPrice();

        assertThat(actual).isEqualTo(0);
    }

    @Test
    public void getShippingPrice_will_return_5000_if_given_order_price_is_less_then_50000() {
        List<Order> orders = Collections.singletonList(
                new PackageItemOrder(new KitPackageItem(9235, "하루의 시작과 끝, 욕실의 포근함을 선사하는 천연 비누", 9900, 22), 1)
        );
        PackageItemPayment sut = new PackageItemPayment(orders);

        int actual = sut.getShippingPrice();

        assertThat(actual).isEqualTo(5000);
    }

    @ParameterizedTest
    @ArgumentsSource(OrdersAndPaymentPriceArguments.class)
    public void getPaymentPrice_will_return_paymentPrice_correctly(
            List<Order> orders, int expected
    ) {
        PackageItemPayment sut = new PackageItemPayment(orders);

        int actual = sut.getPaymentPrice();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void sut_will_throw_IllegalPaymentException_if_given_duplicated_ClassPackageItem_order() {
        List<Order> orders = Arrays.asList(
                new PackageItemOrder(new ClassPackageItem(16374, "스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드 는 비법", 151950), 1),
                new PackageItemOrder(new ClassPackageItem(16374, "스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드 는 비법", 151950), 1)
        );

        assertThrows(IllegalPaymentException.class, () -> {
            new PackageItemPayment(orders);
        });
    }

}