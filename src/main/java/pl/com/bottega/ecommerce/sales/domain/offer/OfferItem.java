/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class OfferItem {

    // product
    private Product product;

    private int quantity;

    private Money totalCost;

    // discount
    private Discount discount;

    public OfferItem(String productId, BigDecimal productPrice, String productName, Date productSnapshotDate, String productType,
            int quantity) {
        this(productId, productPrice, productName, productSnapshotDate, productType, quantity, null, null);
    }

    public OfferItem(String productId, BigDecimal productPrice, String productName, Date productSnapshotDate, String productType,
            int quantity, BigDecimal discount, String discountCause) {
        this.product = new Product(productId, productPrice, productName, productSnapshotDate, productType);

        this.quantity = quantity;
        this.discount = new Discount(discountCause, discount);

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.subtract(discount);
        }

        this.totalCost = new Money(productPrice.multiply(new BigDecimal(quantity))
                                                     .subtract(discountValue));
    }

    public String getProductId() {
        return product.id;
    }

    public BigDecimal getProductPrice() {
        return product.price.value;
    }

    public String getProductName() {
        return product.name;
    }

    public Date getProductSnapshotDate() {
        return product.snapshotDate;
    }

    public String getProductType() {
        return product.type;
    }

    public BigDecimal getTotalCost() {
        return totalCost.value;
    }

    public String getTotalCostCurrency() {
        return totalCost.currency;
    }

    public BigDecimal getDiscount() {
        return discount.value.value;
    }

    public String getDiscountCause() {
        return discount.discountCause;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCost.currency, discount.value.value, discount.discountCause, product.id, product.name, product.price, product.snapshotDate, product.type,
                quantity, totalCost);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OfferItem other = (OfferItem) obj;
        return Objects.equals(totalCost.currency, other.totalCost.currency)
               && Objects.equals(discount, other.discount)
               && Objects.equals(discount.discountCause, other.discount.discountCause)
               && Objects.equals(product.id, other.product.id)
               && Objects.equals(product.name, other.product.name)
               && Objects.equals(product.price, other.product.price)
               && Objects.equals(product.snapshotDate, other.product.snapshotDate)
               && Objects.equals(product.type, other.product.type)
               && quantity == other.quantity
               && Objects.equals(totalCost, other.totalCost);
    }

    /**
     *
     * @param other
     * @param delta
     *            acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (product.price == null) {
            if (other.product.price != null) {
                return false;
            }
        } else if (!product.price.equals(other.product.price)) {
            return false;
        }
        if (product.name == null) {
            if (other.product.name != null) {
                return false;
            }
        } else if (!product.name.equals(other.product.name)) {
            return false;
        }

        if (product.id == null) {
            if (other.product.id != null) {
                return false;
            }
        } else if (!product.id.equals(other.product.id)) {
            return false;
        }
        if (product.type != other.product.type) {
            return false;
        }

        if (quantity != other.quantity) {
            return false;
        }

        BigDecimal max;
        BigDecimal min;
        if (totalCost.value.compareTo(other.totalCost.value) > 0) {
            max = totalCost.value;
            min = other.totalCost.value;
        } else {
            max = other.totalCost.value;
            min = totalCost.value;
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

}
