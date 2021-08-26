package tests.api;

import adapters.MilestoneAdapter;
import adapters.ProjectsAdapter;
import baseEntities.BaseApiTest;
import models.Milestone;

import models.Project;
import models.ProjectTypes;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.List;

public class MilestoneAdapterTests extends BaseApiTest {
     private int milestoneID;
     private int projectID;

    @Test(dependsOnMethods = "addMilestoneAdapterTest")
    public void getOneMilestoneAdapterTest () {
        Milestone actualMilestone = new MilestoneAdapter().get(milestoneID);
        System.out.println(actualMilestone);
    }

    @Test (dependsOnMethods = "addMilestoneAdapterTest")
    public void getMilestonesTest () {
        List<Milestone> milestones = new MilestoneAdapter().getList(projectID);
        milestoneID = milestones.get(0).getId();
        Milestone actualMilestone = new MilestoneAdapter().get(milestoneID);
        System.out.println(actualMilestone);
    }

    @Test
    public void addMilestoneAdapterTest () {
        Project project = Project.builder()
                .name("Anna's project with milestones")
                .suite_mode(ProjectTypes.SINGLE_SUITE_BASELINES)
                .build();

        Project milestoneProject = new ProjectsAdapter().add(project);
        projectID = milestoneProject.getId();

        Milestone milestone = Milestone.builder()
                .name("Anna's added adapter milestone")
                .description("Hey, I am a milestone")
                .project_id(projectID)
                .build();

        Milestone addMilestone = new MilestoneAdapter().post(milestone,projectID);
        milestoneID = addMilestone.getId();

    }

    @Test (dependsOnMethods = "getMilestonesTest")
    public void updateMilestoneAdapterTest () {
        Milestone milestone = Milestone.builder()
                .name("Anna's updated adapter milestone")
                .description("Hey, I am a new milestone")
                .complete_on(new Timestamp(System.currentTimeMillis()))
                .build();
        Milestone updateMilestone = new MilestoneAdapter().update(milestone, milestoneID);
        System.out.println(updateMilestone);
    }


    @Test (dependsOnMethods = "updateMilestoneAdapterTest")
    public void deleteMilestoneAdapterTest () {
        Milestone deleteMilestone = new MilestoneAdapter().delete(milestoneID);
        System.out.println(deleteMilestone);
    }
}

