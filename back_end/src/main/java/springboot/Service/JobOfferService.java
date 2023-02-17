package springboot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.Entity.JobOffer;
import springboot.Repository.JobOfferRepository;

@Service
public class JobOfferService {

	@Autowired
	JobOfferRepository repository;
	
	public void add(JobOffer a) {
		repository.save(a);
	}
	
	public void update(long id,JobOffer a) {
		a.setJobOfferId(id);
		repository.save(a);
	}
	
	public List<JobOffer> viewAll() {
		List<JobOffer> a = repository.findAll();
		return a;
	}
	
	public void delete(long id) {
		repository.deleteById(id);
	}

	public Optional<JobOffer> getById(long id) {
		return repository.findById(id);
	}
	
}
