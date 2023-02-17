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

import springboot.Entity.JobOffer;
import springboot.Service.JobOfferService;

@CrossOrigin
@RestController
public class JobOfferRestController {
	@Autowired
	JobOfferService service;

	@PostMapping("/addJobOffer")
	public boolean add(@RequestBody JobOffer a) {
		service.add(a);
		return true;
	}

	@PutMapping("/updateJobOffer")
	public boolean update(@RequestParam(name="id") long id,@RequestBody JobOffer a) {
		service.update(id, a);
		return true;
	}

	@GetMapping("/viewAllJobOffer")
	public List<JobOffer> viewAll() {
		return service.viewAll();
	}

	@DeleteMapping("/deleteJobOffer")
	public boolean delete(@RequestParam(name="id") long id) {
		service.delete(id);
		return true;
	}
	
	@GetMapping("/getJobOfferById")
	public Optional<JobOffer> getByid(@RequestParam(name="id") long id) {
		return service.getById(id);
	}
	
}
