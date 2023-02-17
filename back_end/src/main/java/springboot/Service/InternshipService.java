package springboot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.Entity.Internship;
import springboot.Entity.enumeration.Status;
import springboot.Repository.InternshipRepository;

@Service
public class InternshipService {

	@Autowired
	InternshipRepository repository;
	
	public void add(Internship a) {
		repository.save(a);
	}
	
	public void update(String id,Internship a) {
		a.setTraineeEmail(id);
		repository.save(a);
	}
	
	public List<Internship> viewAll() {
		List<Internship> a = repository.findAll();
		return a;
	}
	
	public void delete(String id) {
		repository.deleteById(id);
	}

	public Optional<Internship> getById(String id) {
		return repository.findById(id);
	}

	public void response(String id, boolean a) {
		Internship p = repository.getReferenceById(id);
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
