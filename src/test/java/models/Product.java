package models;

public class Product {
    String productName;

    public Product(Builder builder) {
        this.productName = builder.productName;
    }

    public String getProductName() {
        return productName;
    }


    public static final class Builder {
        private String productName;

        public Builder() {
        }

        public Builder(Product origin) {
            this.productName = origin.productName;
        }

        public Builder withProduct(String productName) {
            this.productName = productName;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
