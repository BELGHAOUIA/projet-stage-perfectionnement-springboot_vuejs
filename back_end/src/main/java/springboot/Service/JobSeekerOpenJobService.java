package springboot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.Entity.JobSeekerOpenJob;
import springboot.Entity.enumeration.Status;
import springboot.Entity.id.JobSeekerJobOfferId;
import springboot.Repository.JobSeekerOpenJobRepository;

@Service
public class JobSeekerOpenJobService {

	@Autowired
	JobSeekerOpenJobRepository repository;
	
	public void add(JobSeekerOpenJob a) {
		repository.save(a);
	}
	
	public void update(JobSeekerJobOfferId id,JobSeekerOpenJob a) {
		a.setJobSeekerOpenJobId(id);
		repository.save(a);
	}
	
	public List<JobSeekerOpenJob> viewAll() {
		List<JobSeekerOpenJob> a = repository.findAll();
		return a;
	}
	
	public void delete(JobSeekerJobOfferId id) {
		repository.deleteById(id);
	}

	public List<JobSeekerOpenJob> getAllById(String id) {
		return repository.findByJobSeekerOpenJobId_JobSeekerId(id);
	}
	
	public Optional<JobSeekerOpenJob> getById(JobSeekerJobOfferId id) {
		return repository.findById(id);
	}
	
	public void response(JobSeekerJobOfferId id, boolean a) {
		JobSeekerOpenJob p = repository.getReferenceById(id);
		if(a == true) {
			p.setStatus(Status.ACCEPTED);
		}
		else if(a == false) {
			p.setStatus(Status.REFUSED);
		}
		repository.save(p);
	}

	public long undefined() {
		return repository.countByStatus(Status.UNDEFINED);
	}

	public long refused() {
		return repository.countByStatus(Status.REFUSED);
	}

	public long accepted() {
		return repository.countByStatus(Status.ACCEPTED);
	}
}
