package models;

public class Product {
    String productName;

    private Product( ) {
    }

    public String getProductName() {
        return productName;
    }


    public static  Builder newBuilder (){
        return new Product().new Builder();
    }

    public  class Builder {

        private Builder() {
        }

        public Builder withProduct(String productName) {
            Product.this.productName = productName;
            return this;
        }

        public Product build() {
            return Product.this;
        }
    }
}
