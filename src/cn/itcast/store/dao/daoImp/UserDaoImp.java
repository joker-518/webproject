package cn.itcast.store.dao.daoImp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.store.dao.UserDao;
import cn.itcast.store.domain.Category;
import cn.itcast.store.domain.User;
import cn.itcast.store.utils.JDBCUtils;

public class UserDaoImp implements UserDao {

	@Override
	public void userRegist(User user) throws SQLException {
		String sql="INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?)";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode()};
		qr.update(sql, params);
		
	}

	@Override
	public User userActive(String code)throws SQLException {
		String sql="select * from user where code=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		User user=qr.query(sql, new BeanHandler<User>(User.class),code);
		return user;
	}

	@Override
	public void updateUser(User user) throws SQLException{
		String sql="update user set username=? , password=? ,name =? ,email=?, telephone =? ,birthday =? ,sex=? ,state=? ,code= ? where uid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode(),user.getUid()};
		qr.update(sql,params);
	}

	@Override
	public User userLogin(User user) throws SQLException {
		String sql="select * from user where username=?  and password= ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
	}

	@Override
	public List<User> getAllUsers() throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from user";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<User>(User.class));
	}

	@Override
	public User findById(String uid) throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT uid,username,password,name,email,telephone,birthday,sex,state,code FROM user WHERE uid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		User user=qr.query(sql, new BeanHandler<User>(User.class),uid);
		return  user;
	}

	@Override
	public void delete(String uid) throws Exception {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		//删除用户
	   String sql="delete from user where uid=?";
	   qr.update(sql,uid);
	
	}

}
