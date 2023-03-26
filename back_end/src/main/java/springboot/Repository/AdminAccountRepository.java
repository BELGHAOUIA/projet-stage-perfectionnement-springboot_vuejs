package springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import springboot.Entity.AdminAccount;

public interface AdminAccountRepository extends JpaRepository<AdminAccount, String>{
	
}
