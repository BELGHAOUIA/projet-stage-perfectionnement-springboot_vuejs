package springboot.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springboot.Entity.Internship;
import springboot.Service.InternshipService;

@CrossOrigin
@RestController
public class InternshipRestController {
	@Autowired
	InternshipService service;

	@PostMapping("/addInternship")
	public boolean add(@RequestBody Internship a) {
		service.add(a);
		return true;
	}

	@PutMapping("/updateInternship")
	public boolean update(@RequestParam(name="id") String id,@RequestBody Internship a) {
		service.update(id, a);
		return true;
	}

	@GetMapping("/viewAllInternship")
	public List<Internship> viewAll() {
		return service.viewAll();
	}

	@DeleteMapping("/deleteInternship")
	public boolean delete(@RequestParam(name="id") String id) {
		service.delete(id);
		return true;
	}
	
	@GetMapping("/getInternshipApplicationById")
	public Optional<Internship> getById(@RequestParam(name="id") String id) {
		return service.getById(id);
	}
	
	@PutMapping("/internshipAcceptOrDecline")
	public boolean response(@RequestParam(name="id") String id,@RequestParam(name="response") boolean a) {
		service.response(id, a);
		return true;
	}
	
	@GetMapping("/internshipApplicationUndefined")
	public long undefined() {
		return service.undefined();
	}
	
	@GetMapping("/internshipApplicationRefused")
	public long refused() {
		return service.refused();
	}
	
	@GetMapping("/internshipApplicationAccepted")
	public long accepted() {
		return service.accepted();
	}
}
