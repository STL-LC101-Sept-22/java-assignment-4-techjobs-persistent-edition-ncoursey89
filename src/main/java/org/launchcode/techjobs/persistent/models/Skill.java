package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {
    @ManyToMany(mappedBy = "skills")
    @NotNull
    private List<Job> jobs = new ArrayList<>();
    @Size(max = 500, message = "Description must be under 500 characters.")
    private String description;

    @Size(max = 200, message = "Description must be under 200 characters.")
    public Skill(String description) {
        super();
        this.description = description;
    }
    public Skill() {}
    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}