package hu.sol.java2survey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.sol.java2survey.bean.Student;
import hu.sol.java2survey.dao.StudentDao;

@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;

	public void saveStudent(String name, String email, String subjectId) throws Exception {
		Student student = new Student(name, email, Integer.parseInt(subjectId));
		this.studentDao.saveStudent(student);
	}

	public List<Student> listAllStudent() throws Exception {
		return this.studentDao.listAll();
	}
}
