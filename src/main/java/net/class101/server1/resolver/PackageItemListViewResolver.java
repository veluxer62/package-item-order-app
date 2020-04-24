package net.class101.server1.resolver;

import net.class101.server1.dto.PackageItemsDto;
import net.class101.server1.dto.Response;

import java.util.stream.Collectors;

public class PackageItemListViewResolver implements ViewResolver {
    @Override
    public void show(Response response) {
        String header = "상품번호\t\t상품명\t\t판매가격\t\t재고수";
        String content = ((PackageItemsDto) response).getItems().stream()
                .map(it -> String.format("%d\t\t%s\t\t%d\t\t%d", it.getNumber(), it.getTitle(), it.getPrice(), it.getStock()))
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(header + System.lineSeparator() + content);
    }
}
