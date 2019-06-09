package cn.itcast.store.service.serviceImp;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.store.dao.UserDao;
import cn.itcast.store.dao.daoImp.UserDaoImp;
import cn.itcast.store.domain.User;
import cn.itcast.store.service.UserService;
import cn.itcast.store.utils.BeanFactory;
import cn.itcast.store.utils.JedisUtils;
import redis.clients.jedis.Jedis;

public class UserServiceImp implements UserService {
	
	UserDao UserDao=(UserDao)BeanFactory.createObject("UserDao");

	@Override
	public void userRegist(User user) throws SQLException {
		//实现注册
		UserDao UserDao=(UserDao)BeanFactory.createObject("UserDao");
		UserDao.userRegist(user);
		
	}

	@Override
	public boolean userActive(String code) throws SQLException {
		//实现注册功能
		UserDao UserDao=new UserDaoImp();
		///对DB发送elect * from user where code=?
		User user=UserDao.userActive(code);
		//可以根据激活码查到用户
		if(null!=user){
			//修改用户状态，清除激活码
			user.setState(1);
			user.setCode(null);
			//对数据库进行更新操作  update user set state=1 , code=null where uid=?
			//update user set username=? , password=? ,name =? ,email=?, telephone =? ,birthday =? ,sex=? ,state=? ,code= ? where uid=?
			UserDao.updateUser(user);
			return  true;
		}else{
			//不可以根据激活码查到用户
			return false;
		}
	}

	@Override
	public User userLogin(User user) throws SQLException {
		//
		
		
		UserDao UserDao=new UserDaoImp();
		//select * from user where username=? and password=?
		User uu=UserDao.userLogin(user);
		if(null==uu){
			throw new RuntimeException("用户名不存在!");
		}else if(uu.getState()==0){
			throw new RuntimeException("用户未激活，请激活!");
		}else{
			return uu;
		}
	}

	@Override
	public List<User> getAllUsers() throws Exception {
		// TODO Auto-generated method stub
		return UserDao.getAllUsers();
	}

	@Override
	public void updateUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		UserDao.updateUser(user); 
	}

	@Override
	public User findById(String uid) throws Exception {
		// TODO Auto-generated method stub
		return UserDao.findById(uid);
	}

	@Override
	public void delete(String uid) throws Exception {
		// TODO Auto-generated method stub
		UserDao.delete(uid);
		
	}

}
