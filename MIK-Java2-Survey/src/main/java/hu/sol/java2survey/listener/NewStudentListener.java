package hu.sol.java2survey.listener;

import hu.sol.java2survey.bean.Student;

@FunctionalInterface
public interface NewStudentListener {
	public void handleNewStudent(Student student);
}
