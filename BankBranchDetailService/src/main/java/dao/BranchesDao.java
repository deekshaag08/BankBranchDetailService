package dao;

import java.util.List;
import entities.Branch;


public interface BranchesDao {
	
	public Branch getBranch(String ifsc);
	
	public List<Branch> getBranchList(String bankName, String city);

}
