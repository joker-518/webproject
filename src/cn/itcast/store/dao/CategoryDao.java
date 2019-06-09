package cn.itcast.store.dao;

import java.util.List;

import cn.itcast.store.domain.Category;

public interface CategoryDao {

	List<Category> getAllCats()throws Exception;
	
	void addCategory(Category c)throws Exception;
	
    void updateCategory(Category c)throws Exception;
	
	Category findById(String cid)throws Exception;

	void delete(String cid)throws Exception;
	
}
