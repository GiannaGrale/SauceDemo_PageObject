package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    public String firstname;
    public String lastname;
    public String email;
    public int age;

}
