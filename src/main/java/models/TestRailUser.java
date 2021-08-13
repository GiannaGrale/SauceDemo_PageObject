package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestRailUser {
    private String name;
    private String surname;
    private int id;
    private boolean isActive;
    private String email;

}
