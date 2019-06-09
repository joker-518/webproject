package cn.itcast.store.dao;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.store.domain.User;

public interface UserDao {

	void userRegist(User user)throws SQLException;

	User userActive(String code)throws SQLException;

	void updateUser(User user)throws SQLException;

	User userLogin(User user)throws SQLException;
	
	List<User> getAllUsers()throws SQLException;
	
	User findById(String uid)throws Exception;

	void delete(String uid)throws Exception;


}
