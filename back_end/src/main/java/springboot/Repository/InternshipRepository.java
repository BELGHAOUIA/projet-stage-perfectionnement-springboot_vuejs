package springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.Entity.Internship;
import springboot.Entity.enumeration.Status;

public interface InternshipRepository extends JpaRepository< Internship, String> {
	Long countByStatus(Status status);
}
