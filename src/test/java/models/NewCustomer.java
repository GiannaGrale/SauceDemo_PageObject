package models;

public class NewCustomer {
    String firstName;
    String lastName;
    String zipcode;

    private NewCustomer() {
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


    public static Builder newBuilder() {
        return new NewCustomer().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder withFirstName(String firstName) {
            NewCustomer.this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            NewCustomer.this.lastName = lastName;
            return this;
        }

        public Builder withZipcode(String zipcode) {
            NewCustomer.this.zipcode = zipcode;
            return this;
        }

        public NewCustomer build() {
            return NewCustomer.this;
        }
    }
}
