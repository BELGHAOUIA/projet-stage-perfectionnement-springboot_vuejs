package springboot.Entity;

import springboot.Entity.Heritage.Account;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@AttributeOverride(name = "file", column = @Column(name = "avatar"))
@Table(name = "EmployeeAccount")
public class EmployeeAccount extends Account{
	
	private String employeeDepartment;
	private String employeeRole;

	public String getEmployeeDepartment() {
		return employeeDepartment;
	}
	
	public void setEmployeeDepartment(String employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}
	
	public String getEmployeeRole() {
		return employeeRole;
	}
	
	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}
	
}
