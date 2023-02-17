package springboot.Entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="NewsTeller")
public class NewsTeller {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long newsTellerId;
	private String title;
	
	@CreationTimestamp
	private final LocalDate date = LocalDate.now();
	
	@Lob
	private byte[] newsPdfFile;
	
	public long getNewsTellerId() {
		return newsTellerId;
	}
	
	public void setNewsTellerId(long newsTellerId) {
		this.newsTellerId = newsTellerId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public byte[] getNewsPdfFile() {
		return newsPdfFile;
	}
	
	public void setNewsPdfFile(byte[] newsPdfFile) {
		this.newsPdfFile = newsPdfFile;
	}
	
	
}
