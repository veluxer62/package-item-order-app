package net.class101.server1.domain;

import com.flextrade.jfixture.JFixture;
import net.class101.server1.RandomParameterResolver;
import net.class101.server1.RandomParameterResolver.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(RandomParameterResolver.class)
class ClassPackageItemTest {

    @Test
    public void sut_is_implemented_PackageItem(@Random ClassPackageItem sut) {
        assertThat(sut).isInstanceOf(PackageItem.class);
    }

    @Test
    public void getNumber_will_return_number_correctly(@Random Long number) {
        ClassPackageItem sut = new ClassPackageItem(number, "", 0);
        assertThat(sut.getNumber()).isEqualTo(number);
    }

    @Test
    public void stockAvailable_will_return_true_always(@Random ClassPackageItem sut) {
        assertThat(sut.stockAvailable()).isTrue();
    }

    @Test
    public void getTitle_will_return_title_correctly(@Random String title) {
        ClassPackageItem sut = new ClassPackageItem(0, title, 0);
        assertThat(sut.getTitle()).isEqualTo(title);
    }

    @Test
    public void getPrice_will_return_price_correctly(@Random Integer price) {
        ClassPackageItem sut = new ClassPackageItem(0, "", price);
        assertThat(sut.getPrice()).isEqualTo(price);
    }

}