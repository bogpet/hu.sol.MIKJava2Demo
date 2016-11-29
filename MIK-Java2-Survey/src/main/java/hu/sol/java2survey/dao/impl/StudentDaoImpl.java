package hu.sol.java2survey.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hu.sol.java2survey.bean.Student;
import hu.sol.java2survey.dao.StudentDao;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public void saveStudent(Student student) {
		this.entityManager.persist(student);
		this.entityManager.flush();
	}

	@Override
	public List<Student> listAll() {
		return this.entityManager.createQuery("SELECT t FROM Student t").getResultList();
	}

}
