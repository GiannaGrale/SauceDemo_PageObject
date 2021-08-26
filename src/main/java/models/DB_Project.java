package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DB_Project {
    public String projectName;
    public boolean is_completed;
    public int teamSize;
}
