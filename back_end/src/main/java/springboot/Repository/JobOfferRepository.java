package springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.Entity.JobOffer;

public interface JobOfferRepository extends JpaRepository< JobOffer, Long> {

}
