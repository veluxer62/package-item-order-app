package net.class101.server1.dto;

import net.class101.server1.RandomParameterResolver;
import net.class101.server1.RandomParameterResolver.Random;
import net.class101.server1.Storage;
import net.class101.server1.domain.PackageItem;
import org.assertj.core.internal.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
        List<PackageItem> packageItems = Storage.packageItems;
        PackageItemsDto sut = PackageItemsDto.of(packageItems);
        assertThat(sut).isNotNull();
        assertThat(sut.getItems().size()).isEqualTo(packageItems.size());
    }
}