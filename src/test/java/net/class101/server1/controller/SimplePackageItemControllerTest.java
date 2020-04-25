package net.class101.server1.controller;

import net.class101.server1.dto.PackageItemsDto;
import net.class101.server1.dto.Response;
import net.class101.server1.repository.InMemoryPackageItemRepository;
import net.class101.server1.repository.PackageItemRepository;
import net.class101.server1.service.PackageItemProvider;
import net.class101.server1.service.SimplePackageItemProvider;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimplePackageItemControllerTest {

    private final PackageItemRepository repository = new InMemoryPackageItemRepository();
    private final PackageItemProvider provider = new SimplePackageItemProvider(repository);
    private final SimplePackageItemController sut = new SimplePackageItemController(provider);

    @Test
    public void sut_is_implemented_PackageItemController() {
        assertThat(sut).isInstanceOf(PackageItemController.class);
    }

    @Test
    public void getPackageItems_will_return_PackageItem_list_correctly() {
        Response actual = sut.getPackageItems();
        assertThat(actual).isInstanceOf(PackageItemsDto.class);
    }

}