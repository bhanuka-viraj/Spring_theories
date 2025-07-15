package org.bhanuka.o11_backend.service.impl;

import org.bhanuka.o11_backend.dto.JobDTO;
import org.bhanuka.o11_backend.entity.Job;
import org.bhanuka.o11_backend.repository.JobRepository;
import org.bhanuka.o11_backend.service.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private JobRepository jobRepository;
    private ModelMapper modelMapper;

    public JobServiceImpl (JobRepository jobRepository, ModelMapper modelMapper){
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
    }

    public void saveJob(JobDTO jobDTO){
//        Job job = new Job();
//        job.setId(jobDTO.getId());
//        job.setJobTitle(jobDTO.getJobTitle());
//        job.setJobDescription(jobDTO.getJobDescription());
//        job.setStatus(jobDTO.getStatus());
//        job.setType(jobDTO.getType());
//        job.setCompany(jobDTO.getCompany());
//        job.setLocation(jobDTO.getLocation());
        jobRepository.save(modelMapper.map(jobDTO, Job.class));
    }

    @Override
    public void updateJob(JobDTO jobDTO) {
        jobRepository.findById(jobDTO.getId()).ifPresent(job -> jobRepository.save(modelMapper.map(jobDTO, Job.class)));
    }

    @Override
    public List<JobDTO> getAllJobs() {
        return jobRepository.findAll().stream().map(job -> modelMapper.map(job, JobDTO.class)).toList();
    }

    @Override
    public JobDTO getJobById(Integer id) {
        return jobRepository.findById(id).map(job -> modelMapper.map(job, JobDTO.class)).orElse(null);
    }

    @Override
    public String deleteJobById(Integer id) {
        JobDTO exisitngJob = getJobById(id);
        if (exisitngJob != null) {
            jobRepository.delete(modelMapper.map(exisitngJob, Job.class));
            return "Deleted successfully";
        } else {
            return "Job not found";
        }

    }

    @Override
    public String updateJobStatus(String id) {
        jobRepository.updateJobStatus(id);
        return "Successfully updated";
    }

    @Override
    public List<JobDTO> getAllJobsByKeyword(String keyword) {
        List<Job> jobs = jobRepository.findJobsByCompanyContainingIgnoreCase(keyword);
        return jobs.stream().map(job -> modelMapper.map(job, JobDTO.class)).toList();
    }
}
