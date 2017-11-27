package hu.sol.java2survey.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hu.sol.java2survey.bean.Subject;
import hu.sol.java2survey.dao.SubjectDao;

@Repository
@Transactional
public class SubjectDaoImpl implements SubjectDao {

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjects() throws Exception {
		return this.entityManager.createQuery("SELECT obj FROM Subject obj").getResultList();
	}

}
