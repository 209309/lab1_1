package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;

class Product {

    Product(String id, BigDecimal price, String name, Date snapshotDate, String type) {
        this.id = id;
        this.price = new Money(price);
        this.name = name;
        this.snapshotDate = snapshotDate;
        this.type = type;
    }

    String id;

    Money price;

    String name;

    Date snapshotDate;

    String type;

}
