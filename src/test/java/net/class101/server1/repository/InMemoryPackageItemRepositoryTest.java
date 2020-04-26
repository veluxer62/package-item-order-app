package net.class101.server1.repository;

import net.class101.server1.PackageItemEntity;
import net.class101.server1.Storage;
import net.class101.server1.StorageRecoveryHelper;
import net.class101.server1.domain.KitPackageItem;
import net.class101.server1.domain.PackageItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryPackageItemRepositoryTest {

    private final PackageItemRepository sut = new InMemoryPackageItemRepository();

    @AfterEach
    public void afterEach() {
        StorageRecoveryHelper.recovery();
    }

    @Test
    public void sut_is_implemented_PackageItemRepository() {
        assertThat(sut).isInstanceOf(PackageItemRepository.class);
    }

    @Test
    public void findAll_will_return_PackageItems_correctly() {
        List<PackageItem> actual = sut.findAll();
        assertThat(actual.size()).isEqualTo(Storage.packageItems.size());
    }

    @Test
    public void findByIdIn_will_return_PackageItems_correctly() {
        List<PackageItem> actual = sut.findByIdIn(Arrays.asList(91008L, 16374L));
        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    public void updateStockAll_will_update_stock_of_PackageItems() {
        PackageItem packageItem = new KitPackageItem(91008, "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트", 28000, 10);
        PackageItem packageItem1 = new KitPackageItem(9235, "하루의 시작과 끝, 욕실의 포근함을 선사하는 천연 비누", 9900, 22);

        packageItem.setStock(8);
        packageItem1.setStock(10);

        List<PackageItem> packageItems = Arrays.asList(packageItem, packageItem1);

        sut.updateAll(packageItems);

        List<PackageItemEntity> expected = Storage.packageItems.stream()
                .filter(it -> it.getNumber() == 91008 || it.getNumber() == 9235)
                .collect(Collectors.toList());
        assertThat(expected.get(0).getStock()).isEqualTo(8);
        assertThat(expected.get(1).getStock()).isEqualTo(10);
    }

}