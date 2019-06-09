package cn.itcast.store.dao;


import java.sql.SQLException;

import cn.itcast.store.domain.Admin;



public interface AdminDao {

	Admin login(Admin admin)throws SQLException;
	
}
