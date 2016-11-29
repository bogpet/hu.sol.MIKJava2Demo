package hu.sol.java2survey.dao;

import java.util.List;

import hu.sol.java2survey.bean.Student;

public interface StudentDao {
	public void saveStudent(Student student);

	public List<Student> listAll() throws Exception;
}
