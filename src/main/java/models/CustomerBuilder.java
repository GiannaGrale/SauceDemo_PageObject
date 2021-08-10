package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerBuilder {
    String firstName;
    String emptyFirstName;
    String lastName;
    String zipcode;
}
