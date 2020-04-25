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
    public void getTitle_will_return_title_correctly(@Random String title) {
        KitPackageItem sut = new KitPackageItem(0, title, 0, 0);
        assertThat(sut.getTitle()).isEqualTo(title);
    }

    @Test
    public void getPrice_will_return_price_correctly(@Random Integer price) {
        KitPackageItem sut = new KitPackageItem(0, "", price, 0);
        assertThat(sut.getPrice()).isEqualTo(price);
    }

    @Test
    public void getStock_will_return_price_correctly(@Random Integer stock) {
        KitPackageItem sut = new KitPackageItem(0, "", 0, stock);
        assertThat(sut.getStock()).isEqualTo(stock);
    }

    @Test
    public void setStock_will_change_stock_correctly(@Random Integer stock) {
        KitPackageItem sut = new KitPackageItem(0, "", 0, 0);
        sut.setStock(stock);
        assertThat(sut.getStock()).isEqualTo(stock);
    }
}