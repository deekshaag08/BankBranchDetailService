package controller;

import entities.Branch;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dao.BranchesDao;
import dao.BranchesDaoImpl;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class BankDetailsController {

	
	@RequestMapping(path = "/getBranch/{ifsc}", method = RequestMethod.GET)
	public Branch getBranch(@PathVariable String ifsc) {
       BranchesDao branchesDao = new BranchesDaoImpl();
       Branch branch =  branchesDao.getBranch(ifsc);
       return branch;
    }
    
    @RequestMapping(path = "/getBranches/{bankName}/{city}", method = RequestMethod.GET)
    public List<Branch> getBranches(@PathVariable String bankName, @PathVariable String city) {
       BranchesDao branchesDao = new BranchesDaoImpl();
       List<Branch> branchList =  branchesDao.getBranchList(bankName, city);
       return branchList;
    }
}