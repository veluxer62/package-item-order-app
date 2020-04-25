package net.class101.server1.domain;

import net.class101.server1.RandomParameterResolver;
import net.class101.server1.RandomParameterResolver.Random;
import net.class101.server1.SoldOutException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

class PackageItemOrderTest {

    @Test
    public void sut_is_implemented_Order() {
        PackageItem packageItem = new ClassPackageItem(16374, "스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드 는 비법", 151950);
        PackageItemOrder sut = new PackageItemOrder(packageItem, 1);
        assertThat(sut).isInstanceOf(Order.class);
    }

    @Test
    public void getPrice_will_return_price_correctly_if_given_PackageItem_and_orderCount() {
        PackageItem packageItem = new KitPackageItem(91008, "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트", 28000, 10);
        PackageItemOrder sut = new PackageItemOrder(packageItem, 2);
        int actual = sut.getPrice();
        assertThat(actual).isEqualTo(56000);
    }

    @Test
    @ExtendWith(RandomParameterResolver.class)
    public void getItemTitle_will_return_title_correctly_if_given_PackageItem(@Random String title) {
        PackageItem packageItem = new KitPackageItem(91008, title, 28000, 10);
        PackageItemOrder sut = new PackageItemOrder(packageItem, 2);
        String actual = sut.getItemTitle();
        assertThat(actual).isEqualTo(title);
    }

    @Test
    public void sut_will_throw_SoldOutException_if_given_orderCount_is_less_then_stock_of_KitPackageItem() {
        PackageItem packageItem = new KitPackageItem(91008, "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트", 28000, 10);
        Assertions.assertThrows(SoldOutException.class, () -> {
            new PackageItemOrder(packageItem, 11);
        });
    }

}