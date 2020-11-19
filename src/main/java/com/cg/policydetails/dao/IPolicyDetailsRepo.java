package com.cg.policydetails.dao;
import java.sql.SQLException;
import java.util.List;

import com.cg.policydetails.model.PolicyDetails;
public interface IPolicyDetailsRepo {
PolicyDetails addPolicyDetails(PolicyDetails policyDetails) throws SQLException;
List<PolicyDetails> getAllPolicyDetails() throws SQLException;
PolicyDetails getPolicyDetailsByquestionId(String questionId) throws SQLException;
PolicyDetails updatePolicyDetails(PolicyDetails policyDetails) throws SQLException;
boolean deletePolicyDetails(String questionId) throws SQLException;
}

