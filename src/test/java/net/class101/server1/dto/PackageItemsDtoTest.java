package net.class101.server1.dto;

import net.class101.server1.PackageItemEntity;
import net.class101.server1.RandomParameterResolver;
import net.class101.server1.RandomParameterResolver.Random;
import net.class101.server1.Storage;
import net.class101.server1.domain.ClassPackageItem;
import net.class101.server1.domain.KitPackageItem;
import net.class101.server1.domain.PackageItem;
import org.assertj.core.internal.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(RandomParameterResolver.class)
class PackageItemsDtoTest {

    @Test
    public void sut_is_implemented_Response(@Random List<PackageItem> packageItemList) {
        PackageItemsDto sut = PackageItemsDto.of(packageItemList);
        assertThat(sut).isInstanceOf(Response.class);
    }

    @Test
    public void of_will_return_PackageItemsDto_correctly_if_given_PackageItem_list() {
        List<PackageItem> packageItems = Arrays.asList(
                new ClassPackageItem(65625, "일상에 따뜻한 숨결을 불어넣어요, 반지수와 함께하는 아이패 드 드로잉", 174500),
                new KitPackageItem(91008, "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트", 28000, 10)
        );
        PackageItemsDto sut = PackageItemsDto.of(packageItems);
        assertThat(sut).isNotNull();
        assertThat(sut.getItems().size()).isEqualTo(packageItems.size());
    }
}