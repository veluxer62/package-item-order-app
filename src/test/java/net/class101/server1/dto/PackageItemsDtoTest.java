package net.class101.server1.dto;

import net.class101.server1.RandomParameterResolver;
import net.class101.server1.RandomParameterResolver.Random;
import net.class101.server1.domain.ClassPackageItem;
import net.class101.server1.domain.KitPackageItem;
import net.class101.server1.domain.PackageItem;
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
    public void getBody_will_return_String_correctly() {
        PackageItem packageItem1 = new ClassPackageItem(16374, "스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드 는 비법", 151950);
        KitPackageItem packageIte2 = new KitPackageItem(91008, "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트", 28000, 10);
        PackageItemsDto sut = PackageItemsDto.of(Arrays.asList(packageItem1, packageIte2));

        String actual = (String) sut.getBody();

        String expected = "상품번호\t\t상품명\t\t판매가격\t\t재고수" + System.lineSeparator() +
                "16374\t\t스마트스토어로 월 100만원 만들기, 평범한 사람이 돈을 만드 는 비법\t\t151950\t\t99999" + System.lineSeparator() +
                "91008\t\t작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트\t\t28000\t\t10";
        assertThat(actual).isEqualTo(expected);
    }
}