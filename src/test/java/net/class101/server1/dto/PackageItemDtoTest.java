package net.class101.server1.dto;

import net.class101.server1.RandomParameterResolver;
import net.class101.server1.RandomParameterResolver.Random;
import net.class101.server1.domain.ClassPackageItem;
import net.class101.server1.domain.KitPackageItem;
import net.class101.server1.domain.PackageItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(RandomParameterResolver.class)
class PackageItemDtoTest {

    @Test
    public void of_will_return_dto_correctly_if_given_KitPackageItem(
            @Random Long number,
            @Random String title,
            @Random int price,
            @Random int stock
    ) {
        PackageItem packageItem = new KitPackageItem(number, title, price, stock);
        PackageItemDto actual = PackageItemDto.of(packageItem);

        assertThat(actual).isNotNull();
        assertThat(actual.getNumber()).isEqualTo(number);
        assertThat(actual.getType()).isEqualTo("키트");
        assertThat(actual.getTitle()).isEqualTo(title);
        assertThat(actual.getPrice()).isEqualTo(price);
        assertThat(actual.getStock()).isEqualTo(stock);
    }

    @Test
    public void of_will_return_dto_correctly_if_given_ClassPackageItem(
            @Random Long number,
            @Random String title,
            @Random int price
    ) {
        PackageItem packageItem = new ClassPackageItem(number, title, price);
        PackageItemDto actual = PackageItemDto.of(packageItem);

        assertThat(actual).isNotNull();
        assertThat(actual.getNumber()).isEqualTo(number);
        assertThat(actual.getType()).isEqualTo("키트");
        assertThat(actual.getTitle()).isEqualTo(title);
        assertThat(actual.getPrice()).isEqualTo(price);
        assertThat(actual.getStock()).isEqualTo(99999);
    }

}