package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Branch;

public class BranchesDaoImpl implements BranchesDao{
	
	private Connection connection;
	
	
	public BranchesDaoImpl()
	{
		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/indian_banks_data", "postgres", "deeksha");
		}catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
         /*   Statement statement = connection.createStatement();
            System.out.println("Reading bank records...");
           // System.out.printf("Bank name");
            ResultSet resultSet = statement.executeQuery("SELECT name FROM banks where id<5");
            while (resultSet.next()) {
                System.out.println( resultSet.getString("name"));
            }
 
        } /*catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found.");
            e.printStackTrace();
        }*/ 
	}
	public Branch getBranch(String ifsc)
	{
		Branch branchObj = new Branch();
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT bank_id, branch, address, city, district, state FROM branches where ifsc='" + ifsc +"'";
			ResultSet resultSet = statement.executeQuery(query);
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
		return branchObj;
		
	}
	
	public List<Branch> getBranchList(String bankName, String city)
	{
		List<Branch> branchList= new ArrayList<>();
		
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT bank_id, branch, ifsc, address, city, district,"
							+ " state FROM bank_branches where bank_name='"
							+ bankName +"' AND city='"+ city+ "'";
			ResultSet resultSet = statement.executeQuery(query);
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
		return branchList;
	}
}
