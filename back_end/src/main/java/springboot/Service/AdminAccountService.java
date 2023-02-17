package springboot.Service;

//import java.util.List;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.Entity.AdminAccount;
import springboot.Repository.AdminAccountRepository;

@Service
public class AdminAccountService {

	@Autowired
	AdminAccountRepository repository;
	
	public void add(AdminAccount a) {
		a.setPassword(BCrypt.hashpw(a.getPassword(), BCrypt.gensalt()));
		repository.save(a);
	}
	
	public void update(String id,AdminAccount a) {
		a.setPassword(BCrypt.hashpw(a.getPassword(), BCrypt.gensalt()));
		a.setAdminUserName(id);
		repository.save(a);
	}
	
	/*public List<AdminAccount> viewAll() {
		List<AdminAccount> a = repository.findAll();
		return a;
	}*/
	/*
	public void delete(String id) {
		repository.deleteById(id);
	}*/
	
	public boolean existsByIdAndPass(String id, String a) {
	    if(!repository.existsById(id)) {
	    	return false;
	    }
	    else {
		    AdminAccount adminAccount = repository.getReferenceById(id);
		    return adminAccount.isPassword(a);
	    }
	}
	/*
	public boolean getById(String id) {
		return repository.existsById(id);
	}*/

	public long count() {
		return repository.count();
	}
	
}
