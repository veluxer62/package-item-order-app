package net.class101.server1.repository;

import net.class101.server1.Storage;
import net.class101.server1.domain.ClassPackageItem;
import net.class101.server1.domain.KitPackageItem;
import net.class101.server1.domain.PackageItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InMemoryPackageItemRepositoryTest {

    private final PackageItemRepository sut = new InMemoryPackageItemRepository();

    @Test
    public void sut_is_implemented_PackageItemRepository() {
        assertThat(sut).isInstanceOf(PackageItemRepository.class);
    }

    @Test
    public void findAll_will_return_PackageItems_correctly() {
        List<PackageItem> actual = sut.findAll();
        assertThat(actual).containsAll(Storage.packageItems);
    }

    @Test
    public void update_will_change_PackageItem_correctly() throws ClassNotFoundException {
        KitPackageItem given = new KitPackageItem(91008, "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트", 28000, 3);
        sut.updateStock(given);

        KitPackageItem kitPackageItem = (KitPackageItem) Storage.packageItems
                .stream()
                .filter(it -> it.getNumber() == 91008)
                .collect(Collectors.toList())
                .get(0);

        assertThat(kitPackageItem.getStock()).isEqualTo(3);
    }

    @Test
    public void update_will_throw_exception_if_given_PackageItem_is_not_exist() {
        KitPackageItem given = new KitPackageItem(-1, "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트", 28000, 3);
        assertThrows(ClassNotFoundException.class, () -> sut.updateStock(given));
    }

    @Test
    public void update_will_throw_exception_if_given_PackageItem_is_ClassPacakgeItem() {
        ClassPackageItem given = new ClassPackageItem(16374, "스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드 는 비법", 151950);
        assertThrows(IllegalArgumentException.class, () -> sut.updateStock(given));
    }

    @Test
    public void update_will_throw_exception_if_found_PackageItem_is_ClassPacakgeItem() {
        KitPackageItem given = new KitPackageItem(16374, "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트", 28000, 3);
        assertThrows(ClassCastException.class, () -> sut.updateStock(given));
    }

}