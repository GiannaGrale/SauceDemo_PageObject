package lesson_9.modules;

import java.sql.Timestamp;

public class Project {
    public int id = 16;
    public String name = "Updated Anna's project";
    public boolean show_announcement = false;
    public boolean is_completed = true;
    public Timestamp completed_on = new Timestamp(1625578641);
    public int suite_mode = 1;
    public String url = "https://aqa06onl01.testrail.io/index.php?/projects/overview/16";

    public Project() {
    }
}
