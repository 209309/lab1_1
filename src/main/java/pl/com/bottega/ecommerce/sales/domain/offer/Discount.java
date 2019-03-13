package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

class Discount {

    public Discount(String discountCause, BigDecimal value) {
        this.discountCause = discountCause;
        this.value = new Money(value);
    }

    String discountCause;

    Money value;

}
