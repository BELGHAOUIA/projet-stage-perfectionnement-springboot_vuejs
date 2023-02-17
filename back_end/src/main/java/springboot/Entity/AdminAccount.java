package springboot.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "AdminAccount")
public class AdminAccount {
	
	@Id
	private String adminUserName;
	private String password;
	
	public String getAdminUserName() {
		return adminUserName;
	}
	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isPassword(String plainPassword) {
		return BCrypt.checkpw(plainPassword, password);
	}
	
}
