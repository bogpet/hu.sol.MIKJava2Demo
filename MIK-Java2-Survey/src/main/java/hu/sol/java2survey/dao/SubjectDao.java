package hu.sol.java2survey.dao;

import java.util.List;

import hu.sol.java2survey.bean.Subject;

public interface SubjectDao {

	public List<Subject> getSubjects() throws Exception;
}
