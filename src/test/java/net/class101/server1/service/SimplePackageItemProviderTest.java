package net.class101.server1.service;

import net.class101.server1.Storage;
import net.class101.server1.domain.PackageItem;
import net.class101.server1.repository.InMemoryPackageItemRepository;
import net.class101.server1.repository.PackageItemRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SimplePackageItemProviderTest {

    private final PackageItemRepository repository = new InMemoryPackageItemRepository();
    private final SimplePackageItemProvider sut = new SimplePackageItemProvider(repository);

    @Test
    public void sut_is_implemented_PackageItemProvider() {
        assertThat(sut).isInstanceOf(PackageItemProvider.class);
    }

    @Test
    public void getPackageItems_will_return_PackageItem_correctly() {
        List<PackageItem> actual = sut.getPackageItems();
        assertThat(actual.size()).isEqualTo(Storage.packageItems.size());
    }

}