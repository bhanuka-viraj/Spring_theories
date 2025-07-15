package org.bhanuka.o11_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String jobTitle;
    private String company;
    private String location;
    private String type;
    private String jobDescription;
    private String status;

    public Job(String jobTitle, String company, String location, String type, String jobDescription, String status) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.location = location;
        this.type = type;
        this.jobDescription = jobDescription;
        this.status = status;
    }
    public Job(Integer id, String company, String location, String type, String jobDescription, String status) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.company = company;
        this.location = location;
        this.type = type;
        this.jobDescription = jobDescription;
        this.status = status;
    }

    public Job() {
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
