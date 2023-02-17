package springboot.Entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import springboot.Entity.enumeration.Status;
import springboot.Entity.id.JobSeekerJobOfferId;

@Entity
@Table(name="JobOfferApplication")
public class JobSeekerOpenJob {
	
	 @EmbeddedId
	 private JobSeekerJobOfferId jobSeekerOpenJobId;
	 
	 @Enumerated(EnumType.STRING)
	 private Status status = Status.UNDEFINED;

	public JobSeekerJobOfferId getJobSeekerOpenJobId() {
		return jobSeekerOpenJobId;
	}

	public void setJobSeekerOpenJobId(JobSeekerJobOfferId jobSeekerOpenJobId) {
		this.jobSeekerOpenJobId = jobSeekerOpenJobId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	 
	 
}
