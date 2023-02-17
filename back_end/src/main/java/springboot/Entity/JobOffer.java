package springboot.Entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="JobOffer")
public class JobOffer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long jobOfferId;
	private String title;
	private String description;
	private String department;
	
	@CreationTimestamp
	private final LocalDate date = LocalDate.now();
	
	public long getJobOfferId() {
		return jobOfferId;
	}
	
	public void setJobOfferId(long jobOfferID) {
		this.jobOfferId = jobOfferID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	
}
