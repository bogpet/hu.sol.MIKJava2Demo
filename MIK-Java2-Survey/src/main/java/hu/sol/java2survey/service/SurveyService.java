package hu.sol.java2survey.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.sol.java2survey.bean.Student;
import hu.sol.java2survey.bean.Subject;
import hu.sol.java2survey.dao.StudentDao;
import hu.sol.java2survey.dao.SubjectDao;
import hu.sol.java2survey.listener.NewStudentListener;

@Service
public class SurveyService {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private SubjectDao subjectDao;

	List<NewStudentListener> newStudentEventHandlers = new ArrayList<>();

	public void saveStudent(String name, String email, String subjectId) throws Exception {
		Student student = new Student(name, email, Integer.parseInt(subjectId));
		studentDao.saveStudent(student);
		for (NewStudentListener eventHandler : newStudentEventHandlers) {
			eventHandler.handleNewStudent(student);
		}
	}

	public List<Student> listAllStudent() throws Exception {
		return studentDao.listAll();
	}

	public List<Subject> getSubjects() throws Exception {
		return subjectDao.getSubjects();
	}

	public void addNewStudentListener(NewStudentListener handler) {
		newStudentEventHandlers.add(handler);
	}

	public void removeNewStudentListener(NewStudentListener handler) {
		newStudentEventHandlers.remove(handler);
	}

}
