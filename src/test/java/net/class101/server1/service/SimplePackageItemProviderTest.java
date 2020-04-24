package net.class101.server1.service;

import net.class101.server1.Storage;
import net.class101.server1.domain.PackageItem;
import net.class101.server1.repository.PackageItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SimplePackageItemProviderTest {

    private final PackageItemRepository repository = Mockito.mock(PackageItemRepository.class);

    private final SimplePackageItemProvider sut = new SimplePackageItemProvider(repository);

    @Test
    public void sut_is_implemented_PackageItemProvider() {
        assertThat(sut).isInstanceOf(PackageItemProvider.class);
    }

    @Test
    public void getPackageItems_will_return_PackageItem_correctly() {
        Mockito.when(repository.findAll())
                .thenReturn(Storage.packageItems);

        List<PackageItem> actual = sut.getPackageItems();
        
        assertThat(actual).containsAll(Storage.packageItems);
    }

}