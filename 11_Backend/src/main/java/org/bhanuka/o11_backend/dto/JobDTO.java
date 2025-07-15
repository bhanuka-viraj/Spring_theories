package org.bhanuka.o11_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


public class JobDTO {
    private Integer id;
    private String jobTitle;
    private String company;
    private String location;
    private String type;
    private String jobDescription;
    private String status;

    public JobDTO(Integer id,String jobTitle, String company, String location, String type, String jobDescription, String status) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.company = company;
        this.location = location;
        this.type = type;
        this.jobDescription = jobDescription;
        this.status = status;
    }

    public JobDTO() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
