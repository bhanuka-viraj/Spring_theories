package org.bhanuka.o11_backend.service;

import org.bhanuka.o11_backend.dto.JobDTO;

import java.util.List;

public interface JobService {
     void saveJob(JobDTO jobDTO);
     void updateJob(JobDTO jobDTO);
     List<JobDTO> getAllJobs();
     JobDTO getJobById(Integer id);
     String deleteJobById(Integer id);
     String updateJobStatus(String id);

    List<JobDTO> getAllJobsByKeyword(String company);
}
