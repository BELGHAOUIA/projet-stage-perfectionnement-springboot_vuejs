package springboot.RestController;

import java.io.ByteArrayInputStream;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import springboot.Entity.EmployeeAccount;
import springboot.Service.EmployeeAccountService;

@CrossOrigin
@RestController
public class EmployeeAccountRestController {
	@Autowired
	EmployeeAccountService service;

	@PostMapping("/addEmployeeAccount")
	public boolean add(@RequestBody EmployeeAccount a) {
		service.add(a);
		return true;
	}
				
	@PutMapping("/updateEmployeeAccount")
	public boolean update(@RequestParam(name="id") String id,@RequestBody EmployeeAccount a) {
		service.update(id, a);
		return true;
	}

	@GetMapping("/viewAllEmployeeAccount")
	public List<EmployeeAccount> viewAll() {
		return service.viewAll();
	}
	
	@DeleteMapping("/deleteEmployeeAccount")
	public boolean delete(@RequestParam(name="id") String id) {
		service.delete(id);
		return true;
	}
	
	@PutMapping(value = "/putAvatar")
	public boolean putFile(@RequestParam(name="id") String id,@RequestPart MultipartFile file) {	
		service.putFile(id, file);
		return true;
	}
	
	@GetMapping(value = "/getAvatar", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<InputStreamResource> getPdf(@RequestParam(name="id") String id) {
        Optional<EmployeeAccount> entity = service.getById(id);
        InputStream is = new ByteArrayInputStream(entity.get().getFile());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(new InputStreamResource(is));
    }
	
	@GetMapping("/employeeAccountCount")
	public long count() {
		return service.count();
	}
	
	@GetMapping("/existsById")
	public boolean existsById(@RequestParam(name="id") String id) {
		return service.existsById(id);
	}
	
	@GetMapping("/getEmployeeAccountById") 
	public Optional<EmployeeAccount> getById(@RequestParam(name="id") String id) {
		return service.getById(id);
	}
	
	@GetMapping("/employeeExistsByIdAndPass") 
	public boolean existsByIdAndPass(@RequestParam(name="id") String id, @RequestParam(name="password") String p) {
		Optional<EmployeeAccount> a = service.getById(id);
		if(a.isEmpty()) {
			return false;
		}
		return a.get().isPassword(p);
	}
}
