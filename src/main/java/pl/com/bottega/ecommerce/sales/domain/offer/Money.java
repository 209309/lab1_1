package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

class Money {

    private String currency;

    private BigDecimal value;

    public Money(String currency, BigDecimal value) {
        this.currency = currency;
        this.value = value;
    }

    public Money(BigDecimal value) {
        this.value = value;
        this.currency = "euro";
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getValue() {
        return value;
    }
}
