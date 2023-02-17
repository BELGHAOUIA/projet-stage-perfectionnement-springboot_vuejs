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

import springboot.Entity.JobSeekerAccount;
import springboot.Service.JobSeekerAccountService;

@CrossOrigin
@RestController
public class JobSeekerAccountRestController {
	@Autowired
	JobSeekerAccountService service;

	@PostMapping("/addJobSeekerAccount")
	public boolean add(@RequestBody JobSeekerAccount a) {
		service.add(a);
		return true;
	}
	
	@PutMapping("/putCV")
	public boolean putFile(@RequestParam(name="id") String id,@RequestPart MultipartFile file) {	
		service.putFile(id, file);
		return true;
	}

	@PutMapping("/updateJobSeekerAccount")
	public boolean update(@RequestParam(name="id") String id,@RequestBody JobSeekerAccount a) {
		service.update(id, a);
		return true;
	}

	@GetMapping("/viewAllJobSeekerAccount")
	public List<JobSeekerAccount> viewAll() {
		return service.viewAll();
	}

	@DeleteMapping("/deleteJobSeekerAccount")
	public boolean delete(@RequestParam(name="id") String id) {
		service.delete(id);
		return true;
	}
	
	@GetMapping("/jobSeekerAccountCount")
	public long count() {
		return service.count();
	}
	
	@GetMapping("/getJobSeekerAccountById")
	public Optional<JobSeekerAccount> getById(@RequestParam(name="id") String id) {
		return service.getById(id);
	}
	
	@GetMapping("/jobSeekerExistsById")
	public boolean existsById(@RequestParam(name="id") String id) {
		return service.existsById(id);
	}
	
	@GetMapping("/jobSeekerExistsByIdAndPass") 
	public boolean existsByIdAndPass(@RequestParam(name="id") String id, @RequestParam(name="password") String p) {
	  Optional<JobSeekerAccount> a = service.getById(id);
	  if(a.isEmpty()) {
			return false;
		}
	  return a.get().isPassword(p);
	}
	
	@GetMapping(value = "/getCV", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getPdf(@RequestParam(name="id") String id) {
        Optional<JobSeekerAccount> entity = service.getById(id);
        InputStream is = new ByteArrayInputStream(entity.get().getFile());
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(is));
    }
}
