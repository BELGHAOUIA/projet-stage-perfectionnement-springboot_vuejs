package springboot.RestController;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springboot.Entity.AdminAccount;
import springboot.Service.AdminAccountService;

@CrossOrigin
@RestController
public class AdminAccountRestController {
	@Autowired
	AdminAccountService service;

	@PostMapping("/addAdminAccount")
	public boolean add(@RequestBody AdminAccount a) {
		service.add(a);
		return true;
	}

	@PutMapping("/updateAdminAccount")
	public boolean update(@RequestParam(name="id") String id,@RequestBody AdminAccount a) {
		service.update(id, a);
		return true;
	}
	/*
	@GetMapping("/viewAllAdminAccount")
	public List<AdminAccount> viewAll() {
		return service.viewAll();
	}*/
	/*
	@DeleteMapping("/deleteAdminAccount")
	public boolean delete(@RequestParam(name="id") String id) {
		service.delete(id);
		return true;
	}*/
	
	@GetMapping("/verifyAdminAccountExists")
	public boolean existsByIdAndPass(@RequestParam(name="id") String id,@RequestParam(name="password") String a) {
		return service.existsByIdAndPass(id, a);
	}
	/*
	@GetMapping("/getAdminAccountById")
	public boolean getById(@RequestParam(name="id") String id) {
		return service.getById(id);
	}*/
	
	@GetMapping("/adminAccountCount")
	public long count() {
		return service.count();
	}
}
