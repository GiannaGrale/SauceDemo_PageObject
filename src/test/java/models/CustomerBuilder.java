package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerBuilder {
    String firstName;
    String lastName;
    String zipcode;
}
