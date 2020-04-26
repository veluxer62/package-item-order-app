package net.class101.server1.resolver;

import net.class101.server1.dto.Response;
import net.class101.server1.exception.IllegalOrderException;
import net.class101.server1.exception.ResourceNotFountException;
import net.class101.server1.exception.SoldOutException;

public class ConsoleViewResolver implements ViewResolver {
    @Override
    public void show(Response response) {
        System.out.println(response.getBody());
    }

    @Override
    public void show(Throwable throwable) {
        String message = "주문 실패:" + System.lineSeparator();

        if (throwable instanceof IllegalOrderException) {
            message += throwable.getMessage();
        } else if (throwable instanceof SoldOutException) {
            message += "주문한 상품의 재고가 부족 합니다.";
        } else if (throwable instanceof ResourceNotFountException) {
            message += "입력한 상품 번호의 상품이 존재 하지 않습니다.";
        } else if (throwable instanceof NumberFormatException) {
            message += "입력 값은 숫자 형태로 입력 하여야 합니다.";
        } else {
            message += "오류가 발생 하였습니다. 관리자에게 문의 바랍니다.";
        }

        System.out.println(message);
    }
}
