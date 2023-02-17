package springboot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import springboot.Entity.JobSeekerOpenJob;
import springboot.Entity.enumeration.Status;
import springboot.Entity.id.JobSeekerJobOfferId;

public interface JobSeekerOpenJobRepository extends JpaRepository< JobSeekerOpenJob, JobSeekerJobOfferId> {
	 public List<JobSeekerOpenJob> findByJobSeekerOpenJobId_JobSeekerId(String jobSeekerId);
	 Long countByStatus(Status status);
}
