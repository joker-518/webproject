package cn.itcast.store.service.serviceImp;

import java.sql.SQLException;


import cn.itcast.store.dao.AdminDao;
import cn.itcast.store.dao.daoImp.AdminDaoImp;
import cn.itcast.store.domain.Admin;
import cn.itcast.store.service.AdminService;


public class AdminServiceImp implements AdminService {

	@Override
	public Admin login(Admin admin) throws SQLException {
		AdminDao AdminDao=new AdminDaoImp();
		Admin uu=AdminDao.login(admin);
		if(null==uu){
			throw new RuntimeException("用户名不存在!");
		}
		return uu;
	}

}
