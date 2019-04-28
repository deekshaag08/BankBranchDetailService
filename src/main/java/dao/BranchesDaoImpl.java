package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import entities.Branch;

public class BranchesDaoImpl implements BranchesDao{
	
	//@Value("${spring.datasource.url}")
	private String dbUrl = System.getenv("JDBC_DATABASE_URL");
	//private String dbUrl= "jdbc:postgresql://ec2-54-225-129-101.compute-1.amazonaws.com:5432/d3k3lqaj475u7s?user=crotlljigdjjml&password=bbae5da2de49847ab3dfca307ae2275ddc55316b451b6b3dc8e041615cac7ac6&sslmode=require";
	
	
	private DataSource dataSource;
	
	private Connection connection;
	
	
	public BranchesDaoImpl()
	{
		try {
			dataSource = dataSource();
		}catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
	}
	
	  public DataSource dataSource() throws SQLException {
	    if (dbUrl == null || dbUrl.isEmpty()) {
	      return new HikariDataSource();
	    } else {
	      HikariConfig config = new HikariConfig();
	      config.setJdbcUrl(dbUrl);
	      config.setConnectionTimeout(20000);
	      config.setIdleTimeout(10000);
	      config.setMaxLifetime(20000);
	      return new HikariDataSource(config);
	    }
	  }
	
	public Branch getBranch(String ifsc)
	{
		Branch branchObj = new Branch();
		ResultSet resultSet = null;
		Statement statement = null;
		
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			String query = "SELECT bank_id, branch, address, city, district, state FROM branches where ifsc='" + ifsc +"'";
			resultSet = statement.executeQuery(query);
			if(resultSet.next()) {
                branchObj.setBankId(resultSet.getInt("bank_id"));
                branchObj.setAddress(resultSet.getString("address"));
                branchObj.setName(resultSet.getString("branch"));
                branchObj.setCity(resultSet.getString("city"));
                branchObj.setDistrict(resultSet.getString("district"));
                branchObj.setState(resultSet.getString("state"));
                branchObj.setIfsc(ifsc);
                }
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		    try { resultSet.close(); } catch (Exception e) { /* ignored */ }
		    try { statement.close(); } catch (Exception e) { /* ignored */ }
		    try { connection.close(); } catch (Exception e) { /* ignored */ }
		}
		return branchObj;
		
	}
	
	public List<Branch> getBranchList(String bankName, String city)
	{
		List<Branch> branchList= new ArrayList<>();
		ResultSet resultSet = null;
		Statement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			String query = "SELECT bank_id, branch, ifsc, address, city, district,"
							+ " state FROM bank_branches where bank_name='"
							+ bankName +"' AND city='"+ city+ "'";
			resultSet = statement.executeQuery(query);
			Branch branchObj = new Branch();
			while(resultSet.next()) {
                branchObj.setBankId(resultSet.getInt("bank_id"));
                branchObj.setAddress(resultSet.getString("address"));
                branchObj.setName(resultSet.getString("branch"));
                branchObj.setCity(resultSet.getString("city"));
                branchObj.setDistrict(resultSet.getString("district"));
                branchObj.setState(resultSet.getString("state"));
                branchObj.setIfsc(resultSet.getString("ifsc"));
                branchList.add(branchObj);
                }
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		    try { resultSet.close(); } catch (Exception e) { /* ignored */ }
		    try { statement.close(); } catch (Exception e) { /* ignored */ }
		    try { connection.close(); } catch (Exception e) { /* ignored */ }
		}
		return branchList;
	}
}
