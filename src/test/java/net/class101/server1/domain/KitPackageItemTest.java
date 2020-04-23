package net.class101.server1.domain;

import net.class101.server1.RandomParameterResolver;
import net.class101.server1.RandomParameterResolver.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(RandomParameterResolver.class)
class KitPackageItemTest {

    @Test
    public void sut_is_implemented_PackageItem(@Random KitPackageItem sut) {
        assertThat(sut).isInstanceOf(PackageItem.class);
    }

    @Test
    public void getNumber_will_return_number_correctly(@Random Long number) {
        KitPackageItem sut = new KitPackageItem(number, "", 0, 0);
        assertThat(sut.getNumber()).isEqualTo(number);
    }

    @Test
    public void stockAvailable_will_return_true_if_stock_is_more_than_zero() {
        KitPackageItem sut = new KitPackageItem(0, "", 0, 1);
        assertThat(sut.stockAvailable()).isTrue();
    }

    @Test
    public void stockAvailable_will_return_false_if_stock_is_equal_zero() {
        KitPackageItem sut = new KitPackageItem(0, "", 0, 0);
        assertThat(sut.stockAvailable()).isFalse();
    }

    @Test
    public void stockAvailable_will_return_false_if_stock_is_less_than_zero() {
        KitPackageItem sut = new KitPackageItem(0, "", 0, -1);
        assertThat(sut.stockAvailable()).isFalse();
    }

    @Test
    public void getTitle_will_return_title_correctly(@Random String title) {
        KitPackageItem sut = new KitPackageItem(0, title, 0, 0);
        assertThat(sut.getTitle()).isEqualTo(title);
    }

    @Test
    public void getPrice_will_return_price_correctly(@Random Integer price) {
        KitPackageItem sut = new KitPackageItem(0, "", price, 0);
        assertThat(sut.getPrice()).isEqualTo(price);
    }
}