package baseDBEntity;

import models.DB_Project;
import org.testng.annotations.Test;
import services.DataBaseService;

import java.sql.ResultSet;

public class ProjectsTableAdapter {

    DataBaseService dataBaseService;

    public ProjectsTableAdapter(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }


    public boolean addProject(DB_Project db_project) {
        String insertProjectSQL = String.format("INSERT INTO public.projects (" +
                "name, is_completed, size)" +
                "VALUES ('%s', %b, %d);", db_project.projectName, db_project.is_completed, db_project.teamSize);
        System.out.println(insertProjectSQL);
        return dataBaseService.executeSQL(insertProjectSQL);

    }

    public ResultSet getAllProjects() {
        String projectsSQL = "SELECT * FROM public.projects";
        return dataBaseService.executeQuery(projectsSQL);
    }

    public ResultSet getNameProject() {
        String projectsNameSQL = "SELECT name FROM public.projects";
        return dataBaseService.executeQuery(projectsNameSQL);
    }

    public boolean createProjectTable() {
        String createProjectTableSQL = "CREATE TABLE projects (" +
                "id SERIAL PRIMARY KEY, " +
                "name CHARACTER VARYING(50), " +
                "is_completed BOOLEAN, " +
                "size INTEGER" +
                ");";

        return dataBaseService.executeSQL(createProjectTableSQL);
    }

    public boolean dropProjectTable() {
        String dropTableSQl = "DROP TABLE projects;";
        return dataBaseService.executeSQL(dropTableSQl);

    }
}