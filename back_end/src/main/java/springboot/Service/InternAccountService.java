package springboot.Service;

import java.awt.Color;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.Entity.InternAccount;
import springboot.Entity.Internship;
import springboot.Repository.InternAccountRepository;
import springboot.Repository.InternshipRepository;

@Service
public class InternAccountService {

	@Autowired
	InternAccountRepository repository;
	
	@Autowired
	InternshipRepository internshipRepository;
	
	public void add(InternAccount a) {
		a.setPassword(BCrypt.hashpw(a.getPassword(), BCrypt.gensalt()));
		repository.save(a);
	}
	
	public void update(String id,InternAccount a) {
		a.setPassword(BCrypt.hashpw(a.getPassword(), BCrypt.gensalt()));
		a.setEmail(id);
		repository.save(a);
	}
	
	public List<InternAccount> viewAll() {
		List<InternAccount> a = repository.findAll();
		return a;
	}
	
	public void delete(String id) {
		repository.deleteById(id);
	}
	
	public Optional<InternAccount> getById(String id){
		return repository.findById(id);
	}

	public void acceptInternship(String id) throws IOException {
	       InternAccount a = repository.getReferenceById(id);
	       Internship internship = internshipRepository.getReferenceById(id);
	       
	       String firstName = a.getFirstName();
		   String lastName = a.getLastName();
		   String instituteName = internship.getInstituteName();
		   String intershipType = internship.getIntershipType().toString();
		   String startDate = internship.getStartDate().toString();
		   String endDate = internship.getEndDate().toString();
		   String license = internship.getLicense();
		   
		   PDDocument document = new PDDocument();
		   PDPage firstPage = new PDPage();
		   document.addPage(firstPage);
		   
		   PDImageXObject logoCrj = PDImageXObject.createFromFile("G:\\stage\\Stage Initiation\\logoCrj.png", document);
		   PDImageXObject signature = PDImageXObject.createFromFile("G:\\stage\\StagePerfectionnement\\resources\\signature.jpg", document);

		   
		   PDPageContentStream contentStream = new PDPageContentStream(document, firstPage);
		   
		   float pageHeight = firstPage.getTrimBox().getHeight();
		   float pageWidth = firstPage.getTrimBox().getWidth();

		   contentStream.drawImage(logoCrj, 1, pageHeight - logoCrj.getHeight()/3, logoCrj.getWidth()/3, logoCrj.getHeight()/3);
		   contentStream.drawImage(signature, pageWidth- signature.getWidth(), 150, signature.getWidth()/2, signature.getHeight()/2);
		   
		   contentStream.beginText();
		   contentStream.setFont(PDType1Font.TIMES_ROMAN, 30);
		   contentStream.newLineAtOffset(pageWidth/4, pageHeight - logoCrj.getHeight()/2);  
		   contentStream.setLeading(30.0f);

		   contentStream.showText("Confection Ras Jebel");
		   contentStream.newLine();
		   contentStream.newLine();

		   contentStream.showText("Internship Acceptance Letter");
		   contentStream.newLine();
		   contentStream.endText();
		   
		   contentStream.beginText();
		   contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
		   contentStream.newLineAtOffset(25, 600);  
		   contentStream.setLeading(30.0f);

		   contentStream.showText("Bizerte on:" + LocalDate.now());
		   contentStream.newLine();
		   contentStream.newLine();
		   contentStream.setNonStrokingColor(Color.BLUE);
		   contentStream.setFont(PDType1Font.TIMES_ROMAN, 20);
		   contentStream.showText(instituteName);
		   contentStream.setNonStrokingColor(Color.BLACK);

		   contentStream.newLine();

		   contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
		   contentStream.setLeading(16.0f);
		   
		   contentStream.showText("Dear ");
		   contentStream.setNonStrokingColor(Color.BLUE);
		   contentStream.showText(firstName + " " + lastName);
		   contentStream.setNonStrokingColor(Color.BLACK);
		   contentStream.showText(", ");
		   contentStream.newLine();

		   contentStream.showText("We are delighted to inform you that we have accepted your application for an ");
		   contentStream.setNonStrokingColor(Color.BLUE);
		   contentStream.showText(intershipType );
		   contentStream.setNonStrokingColor(Color.BLACK);
		   contentStream.newLine();

		   contentStream.showText("program. We are impressed by your academic background, particularly your license degree in");
		   contentStream.newLine();
		   contentStream.setNonStrokingColor(Color.BLUE);
		   contentStream.showText(license );
		   contentStream.setNonStrokingColor(Color.BLACK);
		   contentStream.showText(", and believe that you would be a valuable addition to our team.");
		   contentStream.newLine();

		   contentStream.showText("This internship is mandatory for your professional development and will commence on ");
		   contentStream.setNonStrokingColor(Color.BLUE);
		   contentStream.showText(startDate + " to " + endDate + ",");
		   contentStream.newLine();
		   contentStream.setNonStrokingColor(Color.BLACK);

		   contentStream.showText("of the academic years: ");
		   contentStream.setNonStrokingColor(Color.BLUE);
		   contentStream.showText(LocalDate.now().getYear() - 1 + "/" + LocalDate.now().getYear());
		   contentStream.setNonStrokingColor(Color.BLACK);
		   contentStream.showText(" .");
		   contentStream.newLine();
		   contentStream.newLine();

		   contentStream.showText("During this time, you will have the opportunity to work on various projects and enhance  your skills under the guidance");
		   contentStream.newLine();
		   contentStream.showText("of our experienced team members.");
		   contentStream.newLine();
		   contentStream.newLine();

		   
		   contentStream.showText("We wish you the best of luck in your academic studies and future endeavors. We have the utmost respect for "); 
		   contentStream.newLine();

		   contentStream.setNonStrokingColor(Color.BLUE);
		   contentStream.showText(instituteName);
		   contentStream.setNonStrokingColor(Color.BLACK);
		   contentStream.showText(" and are grateful for the opportunity to work with one of its students.");
		   contentStream.newLine();
		   contentStream.newLine();
		   contentStream.newLine();

		   contentStream.showText("Sincerely,");
		   contentStream.newLine();
		   
		   contentStream.showText("M. H. BEN AMEUR");
		   contentStream.newLine();

		   contentStream.showText("Confection Ras Jebel");
		   contentStream.newLine();


		   contentStream.endText();
		   contentStream.close();
		   		   
		   ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	       document.save(byteArrayOutputStream);
		   byteArrayOutputStream.close();
		   
		   byte[] pdfBytes = byteArrayOutputStream.toByteArray();
		  
		   a.setFile(pdfBytes);
		   repository.save(a);
		   
		   document.close();
		   
		System.out.println(" firstPage.getTrimBox().getHeight() = " + firstPage.getTrimBox().getHeight());

	}

	public long count() {
		return repository.count();
	}

	public boolean existsById(String id) {
		return repository.existsById(id);
	}
	
}
