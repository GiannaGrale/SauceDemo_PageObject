package models;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestRailUser {
    @Expose
    private String name;
    @Expose
    private String surname;
    @Expose
    private int id;
    @Expose
    private boolean isActive;
    @Expose
    private String email;

}
