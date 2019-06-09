package cn.itcast.store.service;


import java.sql.SQLException;

import cn.itcast.store.domain.Admin;

public interface AdminService {
	
	Admin login(Admin admin)throws SQLException;

}
