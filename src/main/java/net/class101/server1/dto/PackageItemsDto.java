package net.class101.server1.dto;

import net.class101.server1.domain.PackageItem;

import java.util.List;
import java.util.stream.Collectors;

public class PackageItemsDto implements Response {
    private final List<PackageItemDto> items;

    private PackageItemsDto(List<PackageItemDto> items) {
        this.items = items;
    }

    public static PackageItemsDto of(List<PackageItem> packageItemList) {
        return new PackageItemsDto(
                packageItemList.stream()
                        .map(PackageItemDto::of)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Object getBody() {
        String header = "상품번호\t\t상품명\t\t판매가격\t\t재고수";
        String content = items.stream()
                .map(it -> String.format("%d\t\t%s\t\t%d\t\t%d", it.getNumber(), it.getTitle(), it.getPrice(), it.getStock()))
                .collect(Collectors.joining(System.lineSeparator()));
        return header + System.lineSeparator() + content;
    }
}
