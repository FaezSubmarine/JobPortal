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
	
	private int hibernate;
	
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
			String s = "Insert into JobsPosted (employerID,role,description,salary,relevantExp,datePosted)VALUES(?,?,?,?,?,curdate())";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1,id);
			pstmt.setString(2,role);
			pstmt.setString(3, description);
			pstmt.setString(4,salary);
			pstmt.setString(5,relevantExp);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int addJobsApplied(int empID) {
		try {
			String s = "Insert into jobsapplied (jobID,SeekerID,EmployerID,role,datePosted,dateApplied,status)VALUES(?,?,?,?,?,curdate(),?)";
			pstmt = con.prepareStatement(s);
			
			pstmt.setInt(1, jobID);
			pstmt.setInt(2, id);
			pstmt.setInt(3, empID);
			pstmt.setString(4, role);
			pstmt.setDate(5,datePosted);
			pstmt.setString(6, "pending");
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int deleteJobsApplied() {
		try {
			String s = "Delete from jobsapplied where jobID=? and SeekerID=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, jobID);
			pstmt.setInt(2, id);
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
	public boolean hibernateEmployer(boolean sleep) {
		try {
			String[] tableNames ={"employer","jobsposted","jobsapplied"};
			for(String tableName:tableNames) {
				String idString = (tableName.equals("employer"))?"id":"EmployerID";
				String s = "UPDATE "+tableName+" SET hibernate=? where "+idString+"=?";
				pstmt = con.prepareStatement(s);
				pstmt.setInt(1, sleep?1:0);
				pstmt.setInt(2, id);
				
				if(pstmt.executeUpdate()!=1) {
					return false;
				}
			}
			return true;

		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	public boolean hibernateSeeker(boolean sleep) {
		try {
			String s = "UPDATE seeker SET hibernate=? where id=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, sleep?1:0);
			pstmt.setInt(2, id);
			
			if(pstmt.executeUpdate()!=1) {
				return false;
			}
			s = "UPDATE jobsapplied SET seekerhib=? where SeekerID=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, sleep?1:0);
			pstmt.setInt(2, id);
			if(pstmt.executeUpdate()!=1) {
				return false;
			}
			return true;

		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	public int deleteSeeker() {
		try {
			String s = "DELETE FROM jobsapplied where SeekerID=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
			s = "DELETE FROM seeker where id=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int deleteEmployer() {
		try {
			String s = "DELETE FROM jobsapplied where EmployerID=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
			s = "DELETE FROM jobsposted where EmployerID=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
			s = "DELETE FROM employer where id=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int updateEmployerEmail() {
		try {
			System.out.println("Model email "+email);
			
			String s = "UPDATE employer SET email=? where email=?";
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
			int empId = res.getInt("employerID");
			
			ResultSet empRes = getEmployerResultSet(empId);

			result.put("EmployerID",String.valueOf(empId));
			result.put("company", empRes.getString("company"));
			result.put("EmployerEmail",empRes.getString("email"));
			result.put("role", res.getString("role"));
			result.put("description", res.getString("description"));
			result.put("salary", res.getString("salary"));
			result.put("relevantExp", res.getString("relevantExp"));
			result.put("datePosted", res.getDate("datePosted").toString());
			
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
			String s = "select * from Employer where email=? AND password=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			
			System.out.println("regular password "+passwordScrambler(password));
			System.out.println("asdf password "+passwordScrambler("asdf"));
			
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
	
	public int getSeekerDetails(){
		try {
			String s = "Select * from seeker where id=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, id);
			res = pstmt.executeQuery();
			if(!res.next()) {
				return 0;
			}
			id = res.getInt("id");
			setForSeeker(
			res.getString("firstName"),
			res.getString("lastName"),
			res.getString("password"),
			res.getString("email"),
			res.getString("phoneNumber"),
			res.getString("address"),
			res.getString("SeekStatus"),
			res.getInt("hibernate")
			);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int getEmployerDetails(){
		try {
			String s = "Select * from employer where id=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, id);
			res = pstmt.executeQuery();
			if(!res.next()) {
				return 0;
			}
			id = res.getInt("id");
			setForEmployer(
			res.getString("firstName"),
			res.getString("lastName"),
			res.getString("password"),
			res.getString("email"),
			res.getString("phoneNumber"),
			res.getString("address"),
			res.getString("company"),
			res.getInt("hibernate")
			);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public HashMap<String,String> getEmployerHashMap(){
		try {
			String s = "Select * from employer where id=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, id);
			res = pstmt.executeQuery();
			if(!res.next()) {
				return null;
			}
			HashMap<String,String> result = new HashMap<>();
			result.put("firstName", res.getString("firstName"));
			result.put("lastName", res.getString("lastName"));
			result.put("password", res.getString("password"));
			result.put("email", res.getString("email"));
			result.put("phoneNumber", res.getString("phoneNumber"));
			result.put("address", res.getString("address"));
			result.put("company", res.getString("company"));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	ResultSet getEmployerResultSet(int newID){
		try {
			String s = "Select * from employer where id=?";
			PreparedStatement newPstmt = con.prepareStatement(s);
			newPstmt.setInt(1, newID);
			ResultSet newRes = newPstmt.executeQuery();
			if(!newRes.next()) {
				return null;
			}
			return newRes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	ResultSet getSeekerResultSet(String newID){
		try {
			String s = "Select * from seeker where id=?";
			PreparedStatement newPstmt = con.prepareStatement(s);
			newPstmt.setInt(1, Integer.valueOf(newID));
			ResultSet newRes = newPstmt.executeQuery();
			if(!newRes.next()) {
				return null;
			}
			return newRes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	void setForEmployer(String firstName,
			String lastName,
			String password,
			String email,
			String phoneNumber,
			String address,
			String company,
			int hibernate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.company = company;
		this.hibernate = hibernate;
	}
	void setForSeeker(String firstName,
			String lastName,
			String password,
			String email,
			String phoneNumber,
			String address,
			String seekStatus,
			int hibernate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.seekStatus = seekStatus;
		this.hibernate = hibernate;
	}
	public List<HashMap<String,String>> getJobListingForSeeker(){
		List<HashMap<String,String>> result = new ArrayList<>();
		
		try {
			String s = "Select * from jobsapplied where SeekerID=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, id);
			ResultSet res = pstmt.executeQuery();
			HashSet<Integer> jobIDs = new HashSet<>();
			while(res.next()) {
				jobIDs.add(res.getInt("jobID"));
			}
			
			s = "SELECT * from jobsposted";
			pstmt = con.prepareStatement(s);

			res = pstmt.executeQuery();
			while(res.next()) {
				int employerID = res.getInt("EmployerID");

				ResultSet empRes = getEmployerResultSet(employerID);
				
				if(empRes.getInt("hibernate")==1) {
					continue;
				}
				HashMap<String,String> oneSet = new HashMap<String,String>();
				oneSet.put("EmployerID", String.valueOf(employerID));
				
				oneSet.put("empFirstName", empRes.getString("firstName"));
				oneSet.put("empLastName", empRes.getString("lastName"));
				
				oneSet.put("name", empRes.getString("firstName")+" "+empRes.getString("lastName"));
				
				oneSet.put("company",empRes.getString("company"));
				oneSet.put("EmployerEmail",empRes.getString("email"));
				oneSet.put("role",res.getString("role"));
				oneSet.put("description",res.getString("description"));
				oneSet.put("salary",res.getString("salary"));
				oneSet.put("relevantExp",res.getString("relevantExp"));
				oneSet.put("datePosted",res.getString("datePosted"));
				if(jobIDs.remove((Integer)res.getInt("id"))) {
					oneSet.put("ButtonState", "disable");
					oneSet.put("JobID",res.getString("id"));
				}
				else {
					//oneSet.put("ButtonState",res.getString("enable"));
					oneSet.put("JobID",res.getString("id"));
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
			String s = "Select * from jobsapplied where SeekerID=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, id);
			res = pstmt.executeQuery();
			
			while(res.next()) {
				if(res.getInt("hibernate")==1 || res.getInt("seekerhib")==1) {
					continue;
				}
				HashMap<String, String> eachJob = new HashMap<String, String>();
				eachJob.put("EmployerID", res.getString("EmployerID"));
				ResultSet empRes = getEmployerResultSet(Integer.valueOf(res.getString("EmployerID")));
				eachJob.put("jobID", res.getString("jobID"));
				eachJob.put("name", empRes.getString("firstName")+" "+empRes.getString("lastName"));
				eachJob.put("company", empRes.getString("company"));
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
	public List<HashMap<String,String>> getJobListingForEmployer(){
		List<HashMap<String,String>> result = new ArrayList<>();
		
		try {
			String s = "Select * from jobsapplied where EmployerID=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, id);
			res = pstmt.executeQuery();
			
			while(res.next()) {
				if(res.getInt("seekerhib")==1) {
					continue;
				}
				HashMap<String, String> eachJob = new HashMap<String, String>();
				ResultSet seekerRes = getSeekerResultSet(res.getString("SeekerID"));
				eachJob.put("jobID", res.getString("jobID"));
				eachJob.put("name", seekerRes.getString("firstName")+" "+seekerRes.getString("lastName"));
				eachJob.put("SeekerID", res.getString("SeekerID"));
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
			String s = "Select * from jobsposted where EmployerID=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, id);
			res = pstmt.executeQuery();
			
			while(res.next()) {
				HashMap<String, String> eachJob = new HashMap<String, String>();
				
				eachJob.put("id", res.getString("id"));
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
			String s = "UPDATE employer SET password=? WHERE id=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, passwordScrambler(password));
			pstmt.setInt(2, id);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int updateEmployer() {
		String s = "UPDATE Employer SET firstName=?,lastName=?,email=?,phoneNumber=?,address=?,company=? WHERE id=?";
		
		try {
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, email);
			pstmt.setString(4, phoneNumber);
			pstmt.setString(5, address);
			pstmt.setString(6, company);
			pstmt.setInt(7,id);


			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}
	public int updateSeeker() {
		String s = "UPDATE Seeker SET firstName=?,lastName=?,email=?,phoneNumber=?,address=?,SeekStatus=? WHERE id=?";
		
		try {
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, email);
			pstmt.setString(4, phoneNumber);
			pstmt.setString(5, address);
			pstmt.setString(6, seekStatus);
			pstmt.setInt(7,id);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}
	public int updatePasswordForSeeker() {
		try {
			String s = "UPDATE seeker SET password=? WHERE id=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, passwordScrambler(password));
			pstmt.setInt(2, id);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public boolean checkPasswordOfEmployer() {
		try {
			String s = "Select * from employer where id=? and password=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, id);
			pstmt.setString(2, passwordScrambler(password));
			
			return pstmt.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public boolean checkPasswordOfSeeker() {
		try {
			String s = "Select * from seeker where id=? and password=?";
			pstmt = con.prepareStatement(s);
			pstmt.setInt(1, id);
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
	
	public boolean existingEmail(boolean isSeeker) {
		String subject = isSeeker?"seeker":"employer";
		String s = "Select * from "+subject+" where email=?";
		try {
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, email);
			res = pstmt.executeQuery();
			boolean result = res.next();
			return result;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
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
	public String getScrambledPassword() {
		return passwordScrambler(password);
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
	public void setId(String id) {
		this.id = Integer.valueOf(id);
	}
	
	public String getOldEmail() {
		return oldEmail;
	}

	public void setOldEmail(String oldEmail) {
		this.oldEmail = oldEmail;
	}

	public int getHibernate() {
		return hibernate;
	}

	public void setHibernate(int hibernate) {
		this.hibernate = hibernate;
	}
}
