package com.cg.policydetails.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cg.policydetails.exception.QuestionIdNotFoundException;
import com.cg.policydetails.model.PolicyDetails;

public class PolicyDetailsRepoImplementaion  implements IPolicyDetailsRepo {
	PreparedStatement psmt;
	ResultSet policyResultSet;
	Connection connection;
	
	public PolicyDetailsRepoImplementaion () throws SQLException {
		connection=DatabaseConnection.getConnection();
	}
public PolicyDetails addPolicyDetails(PolicyDetails policyDetails ) throws SQLException {
		
		psmt=connection.prepareStatement("insert into Business_Segment values(?,?,?)");
		psmt.setLong(1, policyDetails.getPolicyNumber());
		psmt.setString(2, policyDetails.getQuestionId());
		psmt.setString(3, policyDetails.getAnswer());
				
		int count=psmt.executeUpdate();
		return policyDetails;
	}
public List<PolicyDetails> getAllPolicyDetails() throws SQLException {
	
	psmt=connection.prepareStatement("select * from PolicyDetails");
		
		policyResultSet=psmt.executeQuery();
		
		List<PolicyDetails> s=new ArrayList<PolicyDetails>();
		while(policyResultSet.next()) {
			PolicyDetails policyDetails=new PolicyDetails();
			policyDetails.setQuestionId(policyResultSet.getString("questionId"));
			policyDetails.setAnswer(policyResultSet.getString("answer"));
			policyDetails.setPolicyNumber(policyResultSet.getInt("policyNumber"));
		s.add(policyDetails);
	}
		return s;
	}

public PolicyDetails updatePolicyDetails(PolicyDetails policyDetails) throws SQLException {
	psmt=connection.prepareStatement("update PolicyQuestions set PolicyNumber=?,QuestionId=?,Answer=?" );
	psmt.setLong(1, policyDetails.getPolicyNumber());
	psmt.setString(2, policyDetails.getQuestionId());
	psmt.setString(3, policyDetails.getAnswer());	
	int count=psmt.executeUpdate();
	return policyDetails;
}
public PolicyDetails getPolicyDetailsByquestionId(String questionId) throws SQLException {
	psmt=connection.prepareStatement("select * from student where questionId=?");
	psmt.setString(1, questionId);
	policyResultSet=psmt.executeQuery();
	if(!policyResultSet.next()) {
		throw new QuestionIdNotFoundException("PolicyDetails with questionId ["+questionId+"] does not exist");
	}
	PolicyDetails policyDetails=new PolicyDetails();
	policyDetails.setQuestionId(policyResultSet.getString("questionId"));
	policyDetails.setAnswer(policyResultSet.getString("answer"));
	policyDetails.setPolicyNumber(policyResultSet.getInt("policyNumber"));
	return policyDetails;
}
public boolean deletePolicyDetails(String questionId) throws SQLException {
	PolicyDetails oldpolicyDetail=getPolicyDetailsByquestionId(questionId);
	psmt=connection.prepareStatement("delete from PolicyDetails where questionId=?");
	psmt.setString(1, questionId);
	int deleted=psmt.executeUpdate();
	return deleted>0;
}
}