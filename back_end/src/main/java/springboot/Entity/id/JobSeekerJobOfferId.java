package springboot.Entity.id;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class JobSeekerJobOfferId implements Serializable {
	
	private static final long serialVersionUID = 1L;
    private String jobSeekerId;
    private long jobOfferId;
    
	public String getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(String jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

	public long getJobOfferId() {
		return jobOfferId;
	}

	public void setJobOfferId(long jobOfferId) {
		this.jobOfferId = jobOfferId;
	}    
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobSeekerJobOfferId jobSeekerJobOfferId = (JobSeekerJobOfferId) o;
        return jobOfferId == jobSeekerJobOfferId.jobOfferId &&
                jobSeekerId.equals(jobSeekerJobOfferId.jobSeekerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobOfferId, jobSeekerId);
    }
	
}
