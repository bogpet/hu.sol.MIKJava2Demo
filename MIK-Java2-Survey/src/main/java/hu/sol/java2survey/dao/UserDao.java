package hu.sol.java2survey.dao;

import java.util.List;

import hu.sol.java2survey.bean.User;

public interface UserDao {
	List<User> listAll();
	User findUserByName(String name);
	void updateUser(User user);
	void saveUser(User user);
	void deleteUser(User user);
}
