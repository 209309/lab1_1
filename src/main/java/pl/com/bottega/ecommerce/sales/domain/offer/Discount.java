package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

class Discount {

    private String cause;

    private Money value;

    Discount(String discountCause, BigDecimal value) {
        this.cause = discountCause;
        this.value = new Money(value);
    }

    public String getCause() {
        return cause;
    }

    public Money getValue() {
        return value;
    }
}
