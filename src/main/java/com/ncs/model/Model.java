package com.ncs.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.sql.Date;

public class Model {
	private int id;
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phoneNumber;
	private String address;
	
	private String company;
	private String seekStatus;
	
	private String role;
	private String description;
	private String salary;
	private String relevantExp;
	
	private int jobID;
	private String applicantEmail;
	private String posterEmail;
	private Date datePosted;
	private Date dateApplied;
	private String status;
	
	private String oldEmail;
	
	static PreparedStatement pstmt = null;
	static Connection con = null;
	
	private ResultSet res;
	public Model() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JobPortal","root","FuckYou1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int registerEmployer() {
		try {
			String s = "Insert into Employer (firstName,lastName,email,password,phoneNumber,address,company)VALUES(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, email);
			pstmt.setString(4, passwordScrambler(password));
			pstmt.setString(5, phoneNumber);
			pstmt.setString(6, address);
			pstmt.setString(7, company);
			
			pstmt.executeUpdate();
			
			s = "select * from employer where email=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			
			res = pstmt.executeQuery();
			res.next();
			id = res.getInt("id");
			
			return 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public int registerSeeker() {
		try {
			String s = "Insert into seeker (firstName,lastName,email,password,phoneNumber,address,SeekStatus)VALUES(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, email);
			pstmt.setString(4, passwordScrambler(password));
			pstmt.setString(5, phoneNumber);
			pstmt.setString(6, address);
			pstmt.setString(7, seekStatus);
			
			pstmt.executeUpdate();
			
			s = "select * from seeker where email=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			
			res = pstmt.executeQuery();
			res.next();
			id = res.getInt("id");
			
			return 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public int addJobsPosted() {
		try {
			String s = "Insert into JobsPosted (company,EmployerEmail,role,description,salary,relevantExp,datePosted)VALUES(?,?,?,?,?,?,curdate())";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1,company);
			pstmt.setString(2,email);
			pstmt.setString(3,role);
			pstmt.setString(4, description);
			pstmt.setString(5,salary);
			pstmt.setString(6,relevantExp);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int addJobsApplied() {
		try {
			String s = "Insert into jobsapplied (jobID,applicantEmail,posterEmail,role,datePosted,dateApplied,status)VALUES(?,?,?,?,?,curdate(),?)";
			pstmt = con.prepareStatement(s);
			
			pstmt.setInt(1, jobID);
			pstmt.setString(2, applicantEmail);
			pstmt.setString(3, posterEmail);
			pstmt.setString(4, role);
			pstmt.setDate(5,datePosted);
			pstmt.setString(6, "pending");
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateJobStatus() {
		try {
			
			String s = "UPDATE jobsapplied SET status=? WHERE jobID=?";
			
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, status);
			pstmt.setInt(2, jobID);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int deleteJobPosting() {
		try {
			String s = "DELETE FROM jobsposted where id=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
			s = "DELETE FROM jobsapplied where jobID=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int deleteSeeker() {
		try {
			String s = "DELETE FROM jobsapplied where applicantEmail=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			pstmt.executeUpdate();
			
			s = "DELETE FROM seeker where email=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int updateSeekerEmail() {
		try {
			String s = "UPDATE jobsapplied SET applicantEmail=? where applicantEmail=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			pstmt.setString(2,oldEmail);
			pstmt.executeUpdate();
			
			s = "UPDATE seeker SET email=? where email=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			pstmt.setString(2,oldEmail);
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int deleteEmployer() {
		try {
			String s = "DELETE FROM jobsapplied where posterEmail=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			pstmt.executeUpdate();
			
			s = "DELETE FROM jobsposted where EmployerEmail=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			pstmt.executeUpdate();
			
			s = "DELETE FROM employer where email=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int updateEmployerEmail() {
		try {
			System.out.println("Model email "+email);
			
			String s = "UPDATE jobsapplied SET posterEmail=? where posterEmail=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			pstmt.setString(2, oldEmail);

			pstmt.executeUpdate();
			
			s = "UPDATE jobsposted SET EmployerEmail=? where EmployerEmail=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			pstmt.setString(2, oldEmail);

			pstmt.executeUpdate();
			
			s = "UPDATE employer SET email=? where email=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			pstmt.setString(2, oldEmail);

			System.out.println("Updated email");
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public HashMap<String,String> getJobListingFromID(){
		HashMap<String,String> result = new HashMap<String,String>();
		
		try {
			String s = "Select * from jobsposted where id=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, jobID);
			ResultSet res = pstmt.executeQuery();
			res.next();
			
			result.put("company", res.getString("company"));
			result.put("EmployerEmail", res.getString("EmployerEmail"));
			result.put("role", res.getString("role"));
			result.put("description", res.getString("description"));
			result.put("salary", res.getString("salary"));
			result.put("relevantExp", res.getString("relevantExp"));
			result.put("datePosted", res.getDate("datePosted").toString());
			result.put("status", res.getString("status"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public int getJobID() {
		return jobID;
	}
	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
	public int loginEmployer() {
		try {
			String s = "Select * from Employer where email=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			if(!pstmt.executeQuery().next()) {
				return -1;
			}
			s = "select * from Employer where email=? AND password=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			pstmt.setString(2, passwordScrambler(password));
			
			res = pstmt.executeQuery();
			if(!res.next()) {
				return -2;
			}
			id = res.getInt("id");
			firstName = res.getString("firstName");
			lastName = res.getString("lastName");
			password = res.getString("password");
			email = res.getString("email");
			phoneNumber = res.getString("phoneNumber");
			address = res.getString("address");
			company = res.getString("company");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int loginSeeker() {
		try {
			String s = "Select * from seeker where email=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			if(!pstmt.executeQuery().next()) {
				return -1;
			}
			s = "select * from seeker where email=? AND password=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			pstmt.setString(2, passwordScrambler(password));
			
			res = pstmt.executeQuery();
			if(!res.next()) {
				return -2;
			}
			id = res.getInt("id");
			firstName = res.getString("firstName");
			lastName = res.getString("lastName");
			password = res.getString("password");
			email = res.getString("email");
			phoneNumber = res.getString("phoneNumber");
			address = res.getString("address");
			seekStatus = res.getString("SeekStatus");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<HashMap<String,String>> getJobListingForSeeker(){
		List<HashMap<String,String>> result = new ArrayList<>();
		
		try {
			String s = "Select * from jobsapplied where applicantEmail=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			ResultSet res = pstmt.executeQuery();
			HashSet<Integer> jobIDs = new HashSet<>();
			while(res.next()) {
				jobIDs.add(res.getInt("jobID"));
			}
			
			s = "SELECT * from jobsposted";
			pstmt = con.prepareStatement(s);

			res = pstmt.executeQuery();
			while(res.next()) {
				HashMap<String,String> oneSet = new HashMap<String,String>();
				oneSet.put("company",res.getString("company"));
				oneSet.put("EmployerEmail",res.getString("EmployerEmail"));
				oneSet.put("role",res.getString("role"));
				oneSet.put("description",res.getString("description"));
				oneSet.put("salary",res.getString("salary"));
				oneSet.put("relevantExp",res.getString("relevantExp"));
				oneSet.put("datePosted",res.getString("datePosted"));
				if(jobIDs.remove((Integer)res.getInt("id"))) {
					oneSet.put("ButtonState", "disable");
				}
				else {
					oneSet.put("ButtonState",res.getString("id"));
				}
				result.add(oneSet);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<HashMap<String,String>> getJobsAppliedListingForSeeker(){
		List<HashMap<String,String>> result = new ArrayList<>();
		
		try {
			String s = "Select * from jobsapplied where applicantEmail=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				HashMap<String,String> oneSet = new HashMap<String,String>();
				oneSet.put("jobID",res.getString("jobID"));
				oneSet.put("posterEmail",res.getString("posterEmail"));
				oneSet.put("role",res.getString("role"));
				oneSet.put("datePosted",res.getString("datePosted"));
				oneSet.put("dateApplied",res.getString("dateApplied"));
				oneSet.put("status",res.getString("status"));
				result.add(oneSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<HashMap<String,String>> getJobListingForEmployer(){
		List<HashMap<String,String>> result = new ArrayList<>();
		
		try {
			String s = "Select * from jobsapplied where posterEmail=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			res = pstmt.executeQuery();
			
			while(res.next()) {
				HashMap<String, String> eachJob = new HashMap<String, String>();
				
				eachJob.put("jobID", res.getString("jobID"));
				eachJob.put("applicantEmail", res.getString("applicantEmail"));
				eachJob.put("role", res.getString("role"));
				eachJob.put("datePosted", res.getDate("datePosted").toString());
				eachJob.put("dateApplied", res.getDate("dateApplied").toString());
				eachJob.put("status", res.getString("status"));

				result.add(eachJob);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	public List<HashMap<String,String>> getJobPostingForEmployer(){
		List<HashMap<String,String>> result = new ArrayList<>();
		
		try {
			String s = "Select * from jobsposted where EmployerEmail=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			res = pstmt.executeQuery();
			
			while(res.next()) {
				HashMap<String, String> eachJob = new HashMap<String, String>();
				
				eachJob.put("id", res.getString("id"));
				eachJob.put("company", res.getString("company"));
				eachJob.put("EmployerEmail", res.getString("EmployerEmail"));
				eachJob.put("role", res.getString("role"));
				eachJob.put("description", res.getString("description"));
				eachJob.put("salary", res.getString("salary"));
				eachJob.put("relevantExp", res.getString("relevantExp"));
				eachJob.put("datePosted", res.getString("datePosted"));

				result.add(eachJob);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public boolean checkExistingEmailForEmployer() {
		String s = "Select * from employer where email=?";
		try {
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			
			return pstmt.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean checkExistingEmailForEmployerWithPassword() {
		String s = "Select * from employer where email=? and password=?";
		try {
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			pstmt.setString(2, passwordScrambler(password));
			return pstmt.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean checkExistingEmailForEmployerWithDiffID() {
		String s = "Select * from employer where email=? and id<>?";
		try {
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			pstmt.setInt(2, id);
			return pstmt.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkExistingEmailForSeekerWithDiffID() {
		String s = "Select * from seeker where email=? and id<>?";
		try {
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			pstmt.setInt(2, id);
			return pstmt.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean checkExistingEmailForSeeker() {
		String s = "Select * from seeker where email=?";
		try {
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			
			return pstmt.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean checkExistingEmailForSeekerWithPassword() {
		String s = "Select * from seeker where email=? and password=?";
		try {
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			pstmt.setString(2, passwordScrambler(password));
			
			return pstmt.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public int updatePasswordForEmployer() {
		try {
			String s = "UPDATE employer SET password=? WHERE email=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, passwordScrambler(password));
			pstmt.setString(2, email);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int updateEmployer() {
		String s = "UPDATE Employer SET firstName=?,lastName=?,phoneNumber=?,address=?,company=? WHERE id=?";
		
		try {
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, phoneNumber);
			pstmt.setString(4, address);
			pstmt.setString(5, company);
			pstmt.setInt(6,id);
			System.out.println("Model "+id);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}
	public int updateSeeker() {
		String s = "UPDATE Seeker SET firstName=?,lastName=?,phoneNumber=?,address=?,SeekStatus=? WHERE id=?";
		
		try {
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, phoneNumber);
			pstmt.setString(4, address);
			pstmt.setString(5, seekStatus);
			pstmt.setInt(6,id);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}
	public int updatePasswordForSeeker() {
		try {
			String s = "UPDATE seeker SET password=? WHERE email=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, passwordScrambler(password));
			pstmt.setString(2, email);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public boolean checkPasswordOfEmployer() {
		try {
			String s = "Select * from employer where email=? and password=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			pstmt.setString(2, passwordScrambler(password));
			
			return pstmt.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public boolean checkPasswordOfSeeker() {
		try {
			String s = "Select * from seeker where email=? and password=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			pstmt.setString(2, passwordScrambler(password));
			
			return pstmt.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public String passwordScrambler(String subject) {
		char[] result = new char[subject.length()];
		for(int i = 0;i<subject.length();++i) {
			int newInt = (int)subject.charAt(i)+10;
			result[i]=(char)newInt;
		}
		return new String(result);
	}
	
	String passwordDescrambler(String subject) {
		char[] result = new char[subject.length()];
		for(int i = 0;i<subject.length();++i) {
			int newInt = (int)subject.charAt(i)-10;
			result[i]=(char)newInt;
		}
		return new String(result);
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

	public String getSeekStatus() {
		return seekStatus;
	}

	public void setSeekStatus(String seekStatus) {
		this.seekStatus = seekStatus;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getRelevantExp() {
		return relevantExp;
	}

	public void setRelevantExp(String relevantExp) {
		this.relevantExp = relevantExp;
	}

	public String getApplicantEmail() {
		return applicantEmail;
	}

	public void setApplicantEmail(String applicantEmail) {
		this.applicantEmail = applicantEmail;
	}

	public String getPosterEmail() {
		return posterEmail;
	}

	public void setPosterEmail(String posterEmail) {
		this.posterEmail = posterEmail;
	}

	public Date getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(String datePosted) {
		this.datePosted = Date.valueOf(datePosted);
	}

	public Date getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(String dateApplied) {
		this.dateApplied = Date.valueOf(dateApplied);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOldEmail() {
		return oldEmail;
	}

	public void setOldEmail(String oldEmail) {
		this.oldEmail = oldEmail;
	}
}
