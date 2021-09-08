package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {
    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private String announcement;
    @Expose
    private boolean show_announcement;
   // @SerializedName("isCompleted")
    private boolean is_completed;
    @Expose
    private int suite_mode;
    @Expose
    private int completed_on;
    @Expose
    private boolean url;
}
