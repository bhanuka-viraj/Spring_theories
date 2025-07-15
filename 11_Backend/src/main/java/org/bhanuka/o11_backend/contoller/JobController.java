package org.bhanuka.o11_backend.contoller;

import org.bhanuka.o11_backend.dto.JobDTO;
import org.bhanuka.o11_backend.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/job")
public class JobController {
    private final JobService jobServiceImpl;

    public JobController(JobService jobServiceImpl) {
        this.jobServiceImpl = jobServiceImpl;
    }

    @GetMapping
    public List<JobDTO> getAllJobs() {return jobServiceImpl.getAllJobs();}

    @GetMapping("{id}")
    public JobDTO getJobById(@PathVariable("id") Integer id) {return jobServiceImpl.getJobById(id);}

    @GetMapping("search/{keyword}")
    public List<JobDTO> getJobByCompany(@PathVariable("keyword") String company) {return jobServiceImpl.getAllJobsByKeyword(company);}

    @PostMapping
    public String createJob(@RequestBody JobDTO jobDTO) {
        System.out.println("Job: " + jobDTO);
        jobServiceImpl.saveJob(jobDTO);
        return jobDTO.getCompany();
    }

    @PutMapping
    public String updateJob(@RequestBody JobDTO jobDTO) {
        System.out.println("Job: " + jobDTO);
        jobServiceImpl.updateJob(jobDTO);
        return jobDTO.getCompany();
    }

    @DeleteMapping("{id}")
    public String deleteJob(@PathVariable("id") Integer id) { return jobServiceImpl.deleteJobById(id);
    }

    @PatchMapping("status/{id}")
    public String patchJob(@PathVariable("id") String id) {

        return jobServiceImpl.updateJobStatus(id);
    }
}
