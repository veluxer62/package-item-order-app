package net.class101.server1.resolver;

import net.class101.server1.domain.ClassPackageItem;
import net.class101.server1.domain.KitPackageItem;
import net.class101.server1.domain.PackageItem;
import net.class101.server1.dto.PackageItemsDto;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class PackageItemsDtoAndPrintStringArguments implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                () -> {
                    PackageItem packageItem = new ClassPackageItem(16374, "스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드 는 비법", 151950);
                    PackageItemsDto dto = PackageItemsDto.of(Collections.singletonList(packageItem));

                    String expected = "상품번호\t\t상품명\t\t판매가격\t\t재고수" + System.lineSeparator() +
                            "16374\t\t스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드 는 비법\t\t151950\t\t99999" + System.lineSeparator();
                    return new Object[]{dto, expected};
                },

                () -> {
                    KitPackageItem packageItem = new KitPackageItem(91008, "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트", 28000, 10);
                    PackageItemsDto dto = PackageItemsDto.of(Collections.singletonList(packageItem));

                    String expected = "상품번호\t\t상품명\t\t판매가격\t\t재고수" + System.lineSeparator() +
                            "91008\t\t작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트\t\t28000\t\t10" + System.lineSeparator();
                    return new Object[]{dto, expected};
                },

                () -> {
                    PackageItem packageItem1 = new ClassPackageItem(16374, "스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드 는 비법", 151950);
                    KitPackageItem packageIte2 = new KitPackageItem(91008, "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트", 28000, 10);
                    PackageItemsDto dto = PackageItemsDto.of(Arrays.asList(packageItem1, packageIte2));

                    String expected = "상품번호\t\t상품명\t\t판매가격\t\t재고수" + System.lineSeparator() +
                            "16374\t\t스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드 는 비법\t\t151950\t\t99999" + System.lineSeparator() +
                            "91008\t\t작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트\t\t28000\t\t10" + System.lineSeparator();
                    return new Object[]{dto, expected};
                }
        );
    }
}
