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

import springboot.Entity.NewsTeller;
import springboot.Service.NewsTellerService;

@CrossOrigin
@RestController
public class NewsTellerRestController {
	@Autowired
	NewsTellerService service;

	@PostMapping("/addNewsTeller")
	public long add(@RequestBody NewsTeller a) {
		return	service.add(a);
	}

	@PutMapping("/updateNewsTeller")
	public boolean update(@RequestParam(name="id") long id,@RequestBody NewsTeller a) {
		service.update(id, a);
		return true;
	}

	@GetMapping("/viewAllNewsTeller")
	public List<NewsTeller> viewAll() {
		return service.viewAll();
	}

	@DeleteMapping("/deleteNewsTeller")
	public boolean delete(@RequestParam(name="id") long id) {
		service.delete(id);
		return true;
	}
	
	@PutMapping(value = "/putFile")
	public boolean putFile(@RequestParam(name="id") Long id,@RequestPart MultipartFile file) {	
		service.putFile(id, file);
		return true;
	}
	
	@GetMapping(value = "/getFile", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getPdf(@RequestParam(name="id") long id) {
        Optional<NewsTeller> entity = service.getById(id);
        InputStream is = new ByteArrayInputStream(entity.get().getNewsPdfFile());
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(is));
    }
	
}
