package springboot.Service;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import springboot.Entity.JobSeekerAccount;
import springboot.Repository.JobSeekerAccountRepository;

@Service
public class JobSeekerAccountService {

	@Autowired
	JobSeekerAccountRepository repository;
	
	public void add(JobSeekerAccount a) {
		a.setPassword(BCrypt.hashpw(a.getPassword(), BCrypt.gensalt()));
		repository.save(a);
	}
	
	public void putFile(String id, MultipartFile file) {
		try {
			byte[] bytes = file.getBytes();
			JobSeekerAccount a = repository.getReferenceById(id);
			a.setFile(bytes);
			repository.save(a);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(String id,JobSeekerAccount a) {
		a.setPassword(BCrypt.hashpw(a.getPassword(), BCrypt.gensalt()));
		a.setEmail(id);
		repository.save(a);
	}
	
	public List<JobSeekerAccount> viewAll() {
		List<JobSeekerAccount> a = repository.findAll();
		return a;
	}
	
	public void delete(String id) {
		repository.deleteById(id);
	}

	public long count() {
		return repository.count();
	}

	public Optional<JobSeekerAccount> getById(String id) {
		return repository.findById(id);
	}

	public boolean existsById(String id) {
		return repository.existsById(id);
	}
	
	
}
