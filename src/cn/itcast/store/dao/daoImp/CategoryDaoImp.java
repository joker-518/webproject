package cn.itcast.store.dao.daoImp;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.store.dao.CategoryDao;
import cn.itcast.store.domain.Category;
import cn.itcast.store.utils.JDBCUtils;

public class CategoryDaoImp implements CategoryDao {

	@Override
	public List<Category> getAllCats() throws Exception {
		String sql="select * from category";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Category>(Category.class));
		
	}
	@Override
	public void addCategory(Category c) throws Exception {
		String sql="insert into category values (? ,?)";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,c.getCid(),c.getCname());
	}
	@Override
	public void updateCategory(Category c) throws Exception {
		// TODO Auto-generated method stub
		String sql="update category set cname=? where cid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,c.getCname(),c.getCid());
		
	}
	@Override
	public Category findById(String cid) throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT cid,cname FROM category WHERE cid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Category category=qr.query(sql, new BeanHandler<Category>(Category.class),cid);
		return  category;
	}
	@Override
	public void delete(String cid) throws Exception {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		//将商品的外键设置为null
		String sql="update product set cid=null where cid=?";
		qr.update(sql,cid);
		//删除分类
	   sql="delete from category where cid=?";
	   qr.update(sql,cid);
		
	}

}
