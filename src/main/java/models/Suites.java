package models;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Suites {
    @Expose
    private int id;
    @Expose
    private String name;
}
