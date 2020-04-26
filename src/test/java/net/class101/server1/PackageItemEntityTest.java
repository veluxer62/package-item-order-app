package net.class101.server1;

import net.class101.server1.RandomParameterResolver.Random;
import net.class101.server1.domain.ClassPackageItem;
import net.class101.server1.domain.KitPackageItem;
import net.class101.server1.domain.PackageItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(RandomParameterResolver.class)
class PackageItemEntityTest {

    @Test
    public void of_will_create_PackageItemEntity_if_given_ClassPackageItem(
            @Random long number,
            @Random String title,
            @Random int price
    ) {
        PackageItem packageItem = new ClassPackageItem(number, title, price);
        PackageItemEntity actual = PackageItemEntity.of(packageItem);
        assertThat(actual.getNumber()).isEqualTo(number);
        assertThat(actual.getType()).isEqualTo("클래스");
        assertThat(actual.getTitle()).isEqualTo(title);
        assertThat(actual.getPrice()).isEqualTo(price);
    }

    @Test
    public void of_will_create_PackageItemEntity_if_given_KitPackageItem(
            @Random long number,
            @Random String title,
            @Random int price,
            @Random int stock
    ) {
        PackageItem packageItem = new KitPackageItem(number, title, price, stock);
        PackageItemEntity actual = PackageItemEntity.of(packageItem);
        assertThat(actual.getNumber()).isEqualTo(number);
        assertThat(actual.getType()).isEqualTo("키트");
        assertThat(actual.getTitle()).isEqualTo(title);
        assertThat(actual.getPrice()).isEqualTo(price);
        assertThat(actual.getStock()).isEqualTo(stock);
    }

    @Test
    public void toPackageItem_will_create_PackageItem_if_given_class_type_entity(
            @Random long number,
            @Random String title,
            @Random int price
    ) {
        PackageItemEntity entity = new PackageItemEntity(number, "클래스", title, price, 0);
        PackageItem actual = entity.toPackageItem();

        assertThat(actual).isInstanceOf(ClassPackageItem.class);
        assertThat(actual.getNumber()).isEqualTo(number);
        assertThat(actual.getTitle()).isEqualTo(title);
        assertThat(actual.getPrice()).isEqualTo(price);
    }

    @Test
    public void toPackageItem_will_create_PackageItem_if_given_class_type_entity(
            @Random long number,
            @Random String title,
            @Random int price,
            @Random int stock
    ) {
        PackageItemEntity entity = new PackageItemEntity(number, "키트", title, price, stock);
        PackageItem actual = entity.toPackageItem();

        assertThat(actual).isInstanceOf(KitPackageItem.class);
        assertThat(actual.getNumber()).isEqualTo(number);
        assertThat(actual.getTitle()).isEqualTo(title);
        assertThat(actual.getPrice()).isEqualTo(price);
        assertThat(actual.getStock()).isEqualTo(stock);
    }

}