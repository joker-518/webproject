package cn.itcast.store.service.serviceImp;

import java.util.List;

import cn.itcast.store.dao.CategoryDao;
import cn.itcast.store.domain.Category;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.utils.BeanFactory;
import cn.itcast.store.utils.JedisUtils;
import redis.clients.jedis.Jedis;

public class CategoryServiceImp implements CategoryService {
	
	//CategoryDao CategoryDao=new CategoryDaoImp();
	CategoryDao CategoryDao=(CategoryDao)BeanFactory.createObject("CategoryDao");

	@Override
	public List<Category> getAllCats() throws Exception {
		
		return CategoryDao.getAllCats();
	}

	@Override
	public void addCategory(Category c) throws Exception {
		//本质是向MYSQL插入一条数据
				CategoryDao.addCategory(c);
				//更新redis缓存
				Jedis jedis = JedisUtils.getJedis();
				jedis.del("allCats");
				JedisUtils.closeJedis(jedis);
	}

	@Override
	public void updateCategory(Category c) throws Exception {
		// TODO Auto-generated method stub
		CategoryDao.updateCategory(c); 
		//更新redis缓存
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("allCats");
		JedisUtils.closeJedis(jedis);
		
	}

	@Override
	public Category findById(String cid) throws Exception {
		// TODO Auto-generated method stub
		/*CategoryDao.findById(cid);
		//更新redis缓存
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("allCats");
		JedisUtils.closeJedis(jedis);
		return null;*/
		//更新redis缓存
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("allCats");
		JedisUtils.closeJedis(jedis);
		
		return CategoryDao.findById(cid);
		
	}

	@Override
	public void delete(String cid) throws Exception {
		// TODO Auto-generated method stub
		CategoryDao.delete(cid); 
		//更新redis缓存
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("allCats");
		JedisUtils.closeJedis(jedis);
		
	}


}
