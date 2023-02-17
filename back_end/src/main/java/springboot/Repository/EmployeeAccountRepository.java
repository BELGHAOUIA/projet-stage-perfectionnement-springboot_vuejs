package springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.Entity.EmployeeAccount;

public interface EmployeeAccountRepository extends JpaRepository<EmployeeAccount, String>{

}
