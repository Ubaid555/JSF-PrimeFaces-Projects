import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
import java.sql.*;

@ManagedBean(name = "letterBean")
@RequestScoped
public class LetterBean {
    private String fromAddress;
    private String recipient;
    private Date date;
    private String subject;
    private String body;
    private String salutation;
    private String rank; 
    public String getFromAddress() {
        return fromAddress;
    }
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }
    public String getRecipient() { // Changed from 'getTo' to 'getRecipient'
        return recipient;
    }
    public void setRecipient(String recipient) { // Changed from 'setTo' to 'setRecipient'
        this.recipient = recipient;
    }
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {	
		this.rank = rank;
	}
	public void submitLetter() {
        try (Connection con = JdbcUtil.getConnection()) {
            if (con != null) {
                System.out.println("Connection successful");
                String insertQuery = "INSERT INTO letter (from_address, recipient, date, subject, body, salutation, rank) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pst = con.prepareStatement(insertQuery)) {
                    pst.setString(1, this.fromAddress);
                    pst.setString(2, this.recipient);
                    pst.setDate(3, new java.sql.Date(this.date.getTime()));
                    pst.setString(4, this.subject);
                    pst.setString(5, this.body);
                    pst.setString(6, this.salutation);
                    pst.setString(7, this.rank);
                    pst.executeUpdate();
                    System.out.println("Data Saved Succesful");
                }
            }
        } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	}