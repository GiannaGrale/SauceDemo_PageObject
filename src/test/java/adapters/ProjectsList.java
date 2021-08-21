package adapters;

import lombok.Data;
import models.Project;

import java.util.List;

@Data
public class ProjectsList {
    List<Project> data;
}
