package springboot.RestController;

import java.io.ByteArrayInputStream;


import java.io.IOException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springboot.Entity.InternAccount;
import springboot.Service.InternAccountService;

@CrossOrigin
@RestController
public class InternAccountRestController {
	@Autowired
	InternAccountService service;

	@PostMapping("/addInternAccount")
	public boolean add(@RequestBody InternAccount a) {
		service.add(a);
		return true;
	}

	@PutMapping("/updateInternAccount")
	public boolean update(@RequestParam(name="id") String id,@RequestBody InternAccount a) {		
		service.update(id, a);
		return true;
	}

	@GetMapping("/viewAllInternAccount")
	public List<InternAccount> viewAll() {
		return service.viewAll();
	}
	

	@DeleteMapping("/deleteInternAccount")
	public boolean delete(@RequestParam(name="id") String id) {
		service.delete(id);
		return true;
	}
	
	@GetMapping(value = "/getAcceptedInternship", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getPdf(@RequestParam(name="id") String id) {
        Optional<InternAccount> entity = service.getById(id);
        InputStream is = new ByteArrayInputStream(entity.get().getFile());
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(is));
    }
	
	@PutMapping("/acceptInternship") 
	public boolean acceptInternship(@RequestParam(name="id") String id) throws IOException {	
		service.acceptInternship(id);
		return true;
	}
	
	@GetMapping("/internAccountCount")
	public long count() {
		return service.count();
	}
	
	@GetMapping("/getInternAccountById/{id}")
	public Optional<InternAccount> getById(@PathVariable String id) {
		return service.getById(id);
	}
	
	@GetMapping("/traineeExistsById")
	public boolean existsById(@RequestParam(name="id") String id) {
		return service.existsById(id);
	}
	
	@GetMapping("/traineeExistsByIdAndPass") 
	public boolean existsByIdAndPass(@RequestParam(name="id") String id, @RequestParam(name="password") String p) {
	  Optional<InternAccount> a = service.getById(id);
	  if(a.isEmpty()) {
			return false;
		}
	  return a.get().isPassword(p);
	}
}
