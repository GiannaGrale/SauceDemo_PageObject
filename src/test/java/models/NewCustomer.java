package models;

public class NewCustomer {
    String firstName;
    String lastName;
    String zipcode;

    public NewCustomer(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.zipcode = builder.zipcode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getZipcode() {
        return zipcode;
    }

    public static final class Builder {
        private String firstName;
        private String lastName;
        private String zipcode;

        public Builder() {
        }

        public Builder(NewCustomer origin) {
            this.firstName = origin.firstName;
            this.lastName = origin.lastName;
            this.zipcode = origin.zipcode;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withZipcode(String zipcode) {
            this.zipcode = zipcode;
            return this;
        }

        public NewCustomer build() {
            return new NewCustomer(this);
        }
    }
}
