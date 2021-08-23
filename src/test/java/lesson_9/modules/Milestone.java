package lesson_9.modules;

import java.sql.Timestamp;
import java.util.List;

public class Milestone {
    public int id = 6;
    public String name = "Milestone_3";
    public String description = "text";
    public Timestamp start_on = null;
    public Timestamp started_on = new Timestamp(1625577456);
    public boolean is_started = true;
    public Timestamp due_on = null;
    public boolean is_completed = false;
    public Timestamp completed_on = null;
    public int project_id = 16;
    public int parent_id;
    public String refs = null;
    public String url = "https://aqa06onl01.testrail.io/index.php?/milestones/view/6</url>";
    public List<Object> milestones;

    public Milestone() {
    }
}

