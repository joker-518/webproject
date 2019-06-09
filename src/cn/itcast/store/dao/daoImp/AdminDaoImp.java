package cn.itcast.store.dao.daoImp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


import cn.itcast.store.dao.AdminDao;
import cn.itcast.store.domain.Admin;
import cn.itcast.store.utils.JDBCUtils;

public class AdminDaoImp implements AdminDao {

	@Override
	public Admin login(Admin admin) throws SQLException {
		String sql="select * from admin where name=?  and password= ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Admin>(Admin.class),admin.getName(),admin.getPassword());
		
	}

}
