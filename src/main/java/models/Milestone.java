package models;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class Milestone{
    @Expose
    private int id;
    private int project_id;
    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private Timestamp complete_on;

}
