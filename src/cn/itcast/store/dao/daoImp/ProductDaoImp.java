package cn.itcast.store.dao.daoImp;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.store.dao.ProductDao;
import cn.itcast.store.domain.Product;
import cn.itcast.store.utils.JDBCUtils;

public class ProductDaoImp implements ProductDao {

	@Override
	public void saveProduct(Product product) throws Exception {
		String sql="INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?)";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid()};
		qr.update(sql,params);
	}

	@Override
	public List<Product> findAllProductsWithPage(int startIndex, int pageSize) throws Exception {
		String sql="select * from product order by pdate desc limit  ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),startIndex,pageSize);
	}

	@Override
	public int findTotalRecords() throws Exception {
		String sql="select count(*) from product";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

	@Override
	public Product findProductByPid(String pid) throws Exception {
		String sql="select * from product where pid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Product>(Product.class),pid);
	}
	
	public Product findProductByPname(String pname) throws Exception {
		String sql="select * from product where pname like '%keyword%'";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Product>(Product.class),pname);
	}

	@Override
	public List<Product> findHots() throws Exception {
		String sql="SELECT * FROM product WHERE pflag=0 AND is_hot=1 ORDER BY pdate DESC LIMIT 0 ,9 ";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public List<Product> findNews() throws Exception {
		String sql="SELECT * FROM product WHERE pflag=0 ORDER BY pdate DESC LIMIT 0 , 9 ";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
		
	}

	@Override
	public List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws Exception {
		String sql="select * from product where cid=? limit ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),cid,startIndex,pageSize);
	}

	@Override
	public int findTotalRecords(String cid) throws Exception {
		String sql="select count(*) from product where cid =?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler(),cid);
		return num.intValue();
	}

	@Override
	public void updateProduct(Product p) throws Exception {
		// TODO Auto-generated method stub
		String sql="update product set pname=?,market_price=?,shop_price=?,is_hot=?,pdesc=? where pid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,p.getPname(),p.getMarket_price(),p.getShop_price(),p.getIs_hot(),p.getPdesc(),p.getPid());
		
	}

	@Override
	public List<Product> findPflag() throws Exception {
		// TODO Auto-generated method stub
		String sql="select * FROM product WHERE pflag=1";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public void delProduct(Product p) throws Exception {
		// TODO Auto-generated method stub
		String sql="update product set pflag=1 where pid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,p.getPid());	
	}

	@Override
	public void addProduct(Product p) throws Exception {
		// TODO Auto-generated method stub
		String sql="update product set pflag=0 where pid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,p.getPid());	
		
	}
	
	

}
