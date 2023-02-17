package springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.Entity.JobSeekerAccount;

public interface JobSeekerAccountRepository extends JpaRepository< JobSeekerAccount, String> {

}
