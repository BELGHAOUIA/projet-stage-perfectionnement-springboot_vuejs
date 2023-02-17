package springboot.Service;

import java.io.IOException;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import springboot.Entity.NewsTeller;
import springboot.Repository.NewsTellerRepository;

@Service
public class NewsTellerService {

	@Autowired
	NewsTellerRepository repository;
	
	public long add(NewsTeller a) {
		repository.save(a);
		return a.getNewsTellerId();
	}
	
	public void update(long id,NewsTeller a) {
		a.setNewsTellerId(id);
		repository.save(a);
	}
	
	public List<NewsTeller> viewAll() {
		List<NewsTeller> a = repository.findAll();
		return a;
	}
	
	public void delete(long id) {
		repository.deleteById(id);
	}
	
	public void putFile(Long id, MultipartFile file) {
		try {
			byte[] bytes = file.getBytes();
			NewsTeller a = repository.getReferenceById(id);
			a.setNewsPdfFile(bytes);
			repository.save(a);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Optional<NewsTeller> getById(long id) {
		return repository.findById(id);
	}

}
