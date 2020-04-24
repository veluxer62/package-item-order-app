package net.class101.server1.controller;

import net.class101.server1.Storage;
import net.class101.server1.dto.PackageItemsDto;
import net.class101.server1.dto.Response;
import net.class101.server1.service.PackageItemProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class SimplePackageItemControllerTest {

    private final PackageItemProvider provider = Mockito.mock(PackageItemProvider.class);

    private final SimplePackageItemController sut = new SimplePackageItemController(provider);

    @Test
    public void sut_is_implemented_PackageItemController() {
        assertThat(sut).isInstanceOf(PackageItemController.class);
    }

    @Test
    public void getPackageItems_will_return_PackageItem_list_correctly() {
        Mockito.when(provider.getPackageItems())
                .thenReturn(Storage.packageItems);

        Response actual = sut.getPackageItems();
        assertThat(actual).isInstanceOf(PackageItemsDto.class);
    }

}