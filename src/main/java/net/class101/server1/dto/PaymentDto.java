package net.class101.server1.dto;

import net.class101.server1.domain.Payment;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.Locale;
import java.util.stream.Collectors;

public class PaymentDto implements Response {
    private final Payment payment;

    public PaymentDto(Payment payment) {
        this.payment = payment;
    }

    @Override
    public Object getBody() {
        String body = "주문 내역:" + System.lineSeparator() +
                getLineString() +
                payment.getOrders().stream()
                        .map(it -> it.getPackageItem().getTitle() + " - " + it.getOrderCount() + "개")
                        .collect(Collectors.joining(System.lineSeparator())) + System.lineSeparator() +
                getLineString() +
                "주문금액: " + formatPrice(payment.getOrderPrice()) + "원" + System.lineSeparator();

        if (payment.getShippingPrice() > 0) {
            body += "배송비: " + formatPrice(payment.getShippingPrice()) + "원" + System.lineSeparator();
        }

        body += getLineString() +
                "지불금액: " + formatPrice(payment.getPaymentPrice()) + "원" + System.lineSeparator() +
                getLineString();

        return body;
    }

    private String formatPrice(int price) {
        return NumberFormat.getNumberInstance(Locale.KOREA).format(price);
    }

    private String getLineString() {
        return "-------------------------------------" + System.lineSeparator();
    }
}
