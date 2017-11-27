package hu.sol.java2survey.dao;

import java.util.List;

import hu.sol.java2survey.bean.User;

public interface UserDao {

	public List<User> listAll();

	public User findUserByName(String name);

	public void updateUser(User user);

	public void saveUser(User user);

	public void deleteUser(User user);
}
