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

import springboot.Entity.JobSeekerOpenJob;
import springboot.Entity.id.JobSeekerJobOfferId;
import springboot.Service.JobSeekerOpenJobService;

@CrossOrigin
@RestController
public class JobSeekerOpenJobRestController {
	@Autowired
	JobSeekerOpenJobService service;

	@PostMapping("/addJobSeekerOpenJob")
	public boolean add(@RequestBody JobSeekerOpenJob a) {
		service.add(a);
		return true;
	}

	@PutMapping("/updateJobSeekerOpenJob")
	public boolean update(@RequestParam(name="id") JobSeekerJobOfferId id,@RequestBody JobSeekerOpenJob a) {
		service.update(id, a);
		return true;
	}

	@GetMapping("/viewAllJobSeekerOpenJob")
	public List<JobSeekerOpenJob> viewAll() {
		return service.viewAll();
	}

	@DeleteMapping("/deleteJobSeekerOpenJob")
	public boolean delete(@RequestParam(name="id") JobSeekerJobOfferId id) {
		service.delete(id);
		return true;
	}
	
	@GetMapping("/getAllJobApplicationById")
	public List<JobSeekerOpenJob> getAllById(@RequestParam(name="id") String id) {
		return service.getAllById(id);
	}
	
	@GetMapping("/getJobApplicationById")
	public Optional<JobSeekerOpenJob> getById(@RequestParam(name="id") JobSeekerJobOfferId id) {
		return service.getById(id);
	}
	
	@PutMapping("/jobApplicationAcceptOrDecline")
	public boolean response(@RequestBody JobSeekerJobOfferId id,@RequestParam(name="response") boolean a) {
		service.response(id, a);
		return true;
	}
	
	@GetMapping("/jobOfferApplicationUndefined")
	public long undefined() {
		return service.undefined();
	}
	
	@GetMapping("/jobOfferApplicationRefused")
	public long refused() {
		return service.refused();
	}
	
	@GetMapping("/jobOfferApplicationAccepted")
	public long accepted() {
		return service.accepted();
	}
}
