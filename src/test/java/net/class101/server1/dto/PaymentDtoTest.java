package net.class101.server1.dto;

import net.class101.server1.domain.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentDtoTest {

    @Test
    public void sut_is_implemented_Response() {
        List<Order> orders = Arrays.asList(
                new PackageItemOrder(new KitPackageItem(39712, "집에서 느끼는 겨울의 묵직한 포근함, 플랜테리어 아이템", 17600, 8), 1),
                new PackageItemOrder(new KitPackageItem(9235, "하루의 시작과 끝, 욕실의 포근함을 선사하는 천연 비누", 9900, 22), 2)
        );
        PackageItemPayment payment = new PackageItemPayment(orders);
        PaymentDto sut = new PaymentDto(payment);
        assertThat(sut).isInstanceOf(Response.class);
    }
    
    @Test
    public void print_will_return_String_if_given_order_has_shipping_price() {
        List<Order> orders = Arrays.asList(
                new PackageItemOrder(new KitPackageItem(39712, "집에서 느끼는 겨울의 묵직한 포근함, 플랜테리어 아이템", 17600, 8), 1),
                new PackageItemOrder(new KitPackageItem(9235, "하루의 시작과 끝, 욕실의 포근함을 선사하는 천연 비누", 9900, 22), 2)
        );
        PackageItemPayment payment = new PackageItemPayment(orders);
        PaymentDto sut = new PaymentDto(payment);

        String actual = (String) sut.getBody();
        String expected = "주문 내역:" + System.lineSeparator() +
                "-------------------------------------" + System.lineSeparator() +
                "집에서 느끼는 겨울의 묵직한 포근함, 플랜테리어 아이템 - 1개" + System.lineSeparator() +
                "하루의 시작과 끝, 욕실의 포근함을 선사하는 천연 비누 - 2개" + System.lineSeparator() +
                "-------------------------------------" + System.lineSeparator() +
                "주문금액: 37,400원" + System.lineSeparator() +
                "배송비: 5,000원" + System.lineSeparator() +
                "-------------------------------------" + System.lineSeparator() +
                "지불금액: 42,400원" + System.lineSeparator() +
                "-------------------------------------" + System.lineSeparator();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void print_will_return_String_if_given_order_does_not_has_shipping_price() {
        List<Order> orders = Arrays.asList(
                new PackageItemOrder(new ClassPackageItem(16374, "스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드 는 비법", 151950), 1),
                new PackageItemOrder(new KitPackageItem(39712, "집에서 느끼는 겨울의 묵직한 포근함, 플랜테리어 아이템", 17600, 8), 1),
                new PackageItemOrder(new KitPackageItem(9235, "하루의 시작과 끝, 욕실의 포근함을 선사하는 천연 비누", 9900, 22), 2)
        );
        PackageItemPayment payment = new PackageItemPayment(orders);
        PaymentDto sut = new PaymentDto(payment);

        String actual = (String) sut.getBody();
        String expected = "주문 내역:" + System.lineSeparator() +
                "-------------------------------------" + System.lineSeparator() +
                "스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드 는 비법 - 1개" + System.lineSeparator() +
                "집에서 느끼는 겨울의 묵직한 포근함, 플랜테리어 아이템 - 1개" + System.lineSeparator() +
                "하루의 시작과 끝, 욕실의 포근함을 선사하는 천연 비누 - 2개" + System.lineSeparator() +
                "-------------------------------------" + System.lineSeparator() +
                "주문금액: 189,350원" + System.lineSeparator() +
                "-------------------------------------" + System.lineSeparator() +
                "지불금액: 189,350원" + System.lineSeparator() +
                "-------------------------------------" + System.lineSeparator();
        assertThat(actual).isEqualTo(expected);
    }

}