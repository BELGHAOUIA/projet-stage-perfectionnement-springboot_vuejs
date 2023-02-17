package springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.Entity.InternAccount;

public interface InternAccountRepository  extends JpaRepository< InternAccount, String> {

}
