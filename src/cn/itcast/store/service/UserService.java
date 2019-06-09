package cn.itcast.store.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.store.domain.User;

public interface UserService {

	void userRegist(User user)throws SQLException ;

	boolean userActive(String code)throws SQLException ;

	User userLogin(User user)throws SQLException;
	
	List<User> getAllUsers()throws Exception;
	
	void updateUser(User user)throws SQLException;
	
	User findById(String uid)throws Exception;

	void delete(String uid)throws Exception;

}
