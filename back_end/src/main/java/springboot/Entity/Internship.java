package springboot.Entity;

import springboot.Entity.enumeration.InternshipType;
import springboot.Entity.enumeration.Status;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Table(name="InternshipApplication")
public class Internship {
	
	@Id
	private String traineeEmail;
	
	@Enumerated(EnumType.STRING)
	private InternshipType intershipType;
	private LocalDate  startDate;
	private LocalDate  endDate;
	private String instituteName;
	private String license;
	
    @Enumerated(EnumType.STRING)
	private Status status = Status.UNDEFINED;
	
	
	public String getTraineeEmail() {
		return traineeEmail;
	}

	public void setTraineeEmail(String traineeEmail) {
		this.traineeEmail = traineeEmail;
	}

	public InternshipType getIntershipType() {
		return intershipType;
	}

	public void setIntershipType(InternshipType intershipType) {
		this.intershipType = intershipType;
	}

	public LocalDate getStartDate() {
		return startDate;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public String getInstituteName() {
		return instituteName;
	}
	
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	
	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
