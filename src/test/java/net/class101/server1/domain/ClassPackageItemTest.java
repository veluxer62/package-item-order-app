package net.class101.server1.domain;

import com.flextrade.jfixture.JFixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClassPackageItemTest {

    private final JFixture fixture = new JFixture();

    @Test
    public void sut_is_implemented_PackageItem() {
        ClassPackageItem sut = fixture.create(ClassPackageItem.class);
        assertThat(sut).isInstanceOf(PackageItem.class);
    }

    @Test
    public void getNumber_will_return_number_correctly() {
        Long number = fixture.create(Long.class);
        ClassPackageItem sut = new ClassPackageItem(number, "", 0);
        assertThat(sut.getNumber()).isEqualTo(number);
    }

    @Test
    public void stockAvailable_will_return_true_always() {
        ClassPackageItem sut = fixture.create(ClassPackageItem.class);
        assertThat(sut.stockAvailable()).isTrue();
    }

    @Test
    public void getTitle_will_return_title_correctly() {
        String title = fixture.create(String.class);
        ClassPackageItem sut = new ClassPackageItem(0, title, 0);
        assertThat(sut.getTitle()).isEqualTo(title);
    }

    @Test
    public void getPrice_will_return_price_correctly() {
        Integer price = fixture.create(Integer.class);
        ClassPackageItem sut = new ClassPackageItem(0, "", price);
        assertThat(sut.getPrice()).isEqualTo(price);
    }

}