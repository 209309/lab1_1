package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

class Money {

    public Money(String currency, BigDecimal value) {
        this.currency = currency;
        this.value = value;
    }

    public Money(BigDecimal value) {
        this.value = value;
        this.currency = "euro";
    }

    String currency;

    BigDecimal value;

}
