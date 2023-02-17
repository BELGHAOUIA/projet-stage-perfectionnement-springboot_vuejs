package springboot.Entity;

import springboot.Entity.Heritage.Account;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@AttributeOverride(name = "file", column = @Column(name = "cv"))
@Table(name="JobSeekerAccount")
public class JobSeekerAccount extends Account{
	
}
