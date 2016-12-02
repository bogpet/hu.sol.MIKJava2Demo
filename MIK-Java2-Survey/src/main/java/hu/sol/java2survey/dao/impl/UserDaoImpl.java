package hu.sol.java2survey.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hu.sol.java2survey.bean.User;
import hu.sol.java2survey.dao.UserDao;

@Repository("firstUserDaoImpl")
public class UserDaoImpl implements UserDao {

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listAll() {
		return entityManager.createQuery("SELECT obj FROM User obj").getResultList();
	}

	@Override
	public User findUserByName(String username) {
		Query query = entityManager.createQuery("SELECT obj FROM User obj WHERE obj.username = :username")
				.setParameter("username", username);
		return (User) query.getSingleResult();
	}

	@Override
	public void saveUser(User user) {
		entityManager.persist(user);
		entityManager.flush();
	}

	@Override
	public void updateUser(User user) {
		entityManager.merge(user);
	}

	@Override
	public void deleteUser(User user) {
		entityManager.remove(user);
	}

}
