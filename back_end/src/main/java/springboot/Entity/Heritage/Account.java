package springboot.Entity.Heritage;

import javax.persistence.Id;


import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

import org.mindrot.jbcrypt.BCrypt;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Account {
	
	@Id
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private long phoneNumber;
	
	@Lob
	private byte[] file;

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] pdf) {
		this.file = pdf;
	}
	
	public boolean isPassword(String plainPassword) {
		return BCrypt.checkpw(plainPassword, password);
	}
	
	public void hashPassword() {
		BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
}
