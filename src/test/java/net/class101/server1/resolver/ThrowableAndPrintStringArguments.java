package net.class101.server1.resolver;

import net.class101.server1.exception.IllegalOrderException;
import net.class101.server1.exception.ResourceNotFountException;
import net.class101.server1.exception.SoldOutException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ThrowableAndPrintStringArguments implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                () -> {
                    Throwable exception = new IllegalOrderException("반드시 하나 이상의 주문을 입력해 주세요.");
                    String expected = "주문 실패:" + System.lineSeparator() +
                            "반드시 하나 이상의 주문을 입력해 주세요." + System.lineSeparator();
                    return new Object[]{exception, expected};
                },

                () -> {
                    Throwable exception = new IllegalOrderException("클래스 행사는 하나만 주문 가능 합니다.");
                    String expected = "주문 실패:" + System.lineSeparator() +
                            "클래스 행사는 하나만 주문 가능 합니다." + System.lineSeparator();
                    return new Object[]{exception, expected};
                },

                () -> {
                    Throwable exception = new SoldOutException();
                    String expected = "주문 실패:" + System.lineSeparator() +
                            "주문한 상품의 재고가 부족 합니다." + System.lineSeparator();
                    return new Object[]{exception, expected};
                },

                () -> {
                    Throwable exception = new ResourceNotFountException();
                    String expected = "주문 실패:" + System.lineSeparator() +
                            "입력한 상품 번호의 상품이 존재 하지 않습니다." + System.lineSeparator();
                    return new Object[]{exception, expected};
                },

                () -> {
                    Throwable exception = new NumberFormatException();
                    String expected = "주문 실패:" + System.lineSeparator() +
                            "입력 값은 숫자 형태로 입력 하여야 합니다." + System.lineSeparator();
                    return new Object[]{exception, expected};
                },

                () -> {
                    Throwable exception = new Exception();
                    String expected = "주문 실패:" + System.lineSeparator() +
                            "오류가 발생 하였습니다. 관리자에게 문의 바랍니다." + System.lineSeparator();
                    return new Object[]{exception, expected};
                }
        );
    }
}
