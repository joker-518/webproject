package cn.itcast.store.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import cn.itcast.store.domain.Category;
import cn.itcast.store.domain.PageModel;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.serviceImp.CategoryServiceImp;
import cn.itcast.store.service.serviceImp.ProductServiceImp;
import cn.itcast.store.utils.JDBCUtils;
import cn.itcast.store.utils.MyBeanUtils;
import cn.itcast.store.utils.UUIDUtils;
import cn.itcast.store.utils.UploadUtils;
import cn.itcast.store.web.base.BaseServlet;

public class AdminProductServlet extends BaseServlet {

	// PoiUtil poiUtil = new PoiUtil();
	// findAllProductsWithPage
	public String findAllProductsWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 获取当前页
		int curNum = Integer.parseInt(req.getParameter("num"));
		// 调用业务层查全部商品信息返回PageModel
		ProductService ProductService = new ProductServiceImp();
		PageModel pm = ProductService.findAllProductsWithPage(curNum);
		// 将PageModel放入request
		req.setAttribute("page", pm);
		// 转发到/admin/product/list.jsp
		return "/admin/product/list.jsp";
	}

	public String findPflag(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ProductService ProductService = new ProductServiceImp();
		List<Product> list = ProductService.findPflag();
		// 全部分类信息放入request
		req.setAttribute("pflag", list);
		return "/admin/product/pushDown_list.jsp";
	}

	// addProductUI
	public String addProductUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		CategoryService CategoryService = new CategoryServiceImp();
		// 获取全部分类信息
		List<Category> list = CategoryService.getAllCats();
		// 将全部分类信息放入request
		req.setAttribute("allCats", list);
		// 转发到/admin/product/add.jsp
		return "/admin/product/add.jsp";
	}

	// addProduct
	public String addProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 存储表单中数据
		Map<String, String> map = new HashMap<String, String>();
		// 携带表单中的数据向servcie,dao
		Product product = new Product();
		try {
			// 利用req.getInputStream();获取到请求体中全部数据,进行拆分和封装
			DiskFileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fac);
			List<FileItem> list = upload.parseRequest(req);
			// 4_遍历集合
			for (FileItem item : list) {
				if (item.isFormField()) {
					// 5_如果当前的FileItem对象是普通项
					// 将普通项上name属性的值作为键,将获取到的内容作为值,放入MAP中
					// {username<==>tom,password<==>1234}
					map.put(item.getFieldName(), item.getString("utf-8"));
				} else {
					// 6_如果当前的FileItem对象是上传项

					// 获取到原始的文件名称
					String oldFileName = item.getName();
					// 获取到要保存文件的名称 1222.doc 123421342143214.doc
					String newFileName = UploadUtils.getUUIDName(oldFileName);

					// 通过FileItem获取到输入流对象,通过输入流可以获取到图片二进制数据
					InputStream is = item.getInputStream();
					// 获取到当前项目下products/3下的真实路径
					// D:\tomcat\tomcat71_sz07\webapps\store_v5\products\3
					String realPath = getServletContext().getRealPath("/products/3/");
					String dir = UploadUtils.getDir(newFileName); // /f/e/d/c/4/9/8/4
					String path = realPath + dir; // D:\tomcat\tomcat71_sz07\webapps\store_v5\products\3/f/e/d/c/4/9/8/4
					// 内存中声明一个目录
					File newDir = new File(path);
					if (!newDir.exists()) {
						newDir.mkdirs();
					}
					// 在服务端创建一个空文件(后缀必须和上传到服务端的文件名后缀一致)
					File finalFile = new File(newDir, newFileName);
					if (!finalFile.exists()) {
						finalFile.createNewFile();
					}
					// 建立和空文件对应的输出流
					OutputStream os = new FileOutputStream(finalFile);
					// 将输入流中的数据刷到输出流中
					IOUtils.copy(is, os);
					// 释放资源
					IOUtils.closeQuietly(is);
					IOUtils.closeQuietly(os);
					// 向map中存入一个键值对的数据 userhead<===> /image/11.bmp
					// {username<==>tom,password<==>1234,userhead<===>image/11.bmp}
					map.put("pimage", "/products/3/" + dir + "/" + newFileName);
				}
			}

			// 7_利用BeanUtils将MAP中的数据填充到Product对象上
			BeanUtils.populate(product, map);
			product.setPid(UUIDUtils.getId());
			product.setPdate(new Date());
			product.setPflag(0);

			// 8_调用servcie_dao将user上携带的数据存入数据仓库,重定向到查询全部商品信息路径
			ProductService ProductService = new ProductServiceImp();
			ProductService.saveProduct(product);

			resp.sendRedirect("/store_v5/AdminProductServlet?method=findAllProductsWithPage&num=1");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String updateProductUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String pid = req.getParameter("pid");
		ProductService productService = new ProductServiceImp();
		Product product = productService.findProductByPid(pid);
		// 显示编辑菜单
		req.setAttribute("product", product);
		return "/admin/product/update.jsp";
	}

	public String updateProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 存储表单中数据
		Map<String, String> map = new HashMap<String, String>();
		// 携带表单中的数据向servcie,dao
		Product product = new Product();
		try {
			// 利用req.getInputStream();获取到请求体中全部数据,进行拆分和封装
			DiskFileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fac);
			List<FileItem> list = upload.parseRequest(req);
			// 4_遍历集合
			for (FileItem item : list) {
				if (item.isFormField()) {
					// 5_如果当前的FileItem对象是普通项
					// 将普通项上name属性的值作为键,将获取到的内容作为值,放入MAP中
					// {username<==>tom,password<==>1234}
					map.put(item.getFieldName(), item.getString("utf-8"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 7_利用BeanUtils将MAP中的数据填充到Product对象上
		BeanUtils.populate(product, map);
		if (product.getIs_hot() == 0) {
			product.setIs_hot(0);
		} else if (product.getIs_hot() == 1) {
			product.setIs_hot(1);
		}
		// product.setPid(UUIDUtils.getId());
		product.setPdate(new Date());
		product.setPflag(0);

		// 调用业务层添加分类功能
		ProductService productService = new ProductServiceImp();
		productService.updateProduct(product);
		// 重定向到查询全部商品信息
		resp.sendRedirect(req.getContextPath() + "/AdminProductServlet?method=findAllProductsWithPage&num=1");
		return null;
	}

	// 下架商品
	public String del(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		// 调用业务层功能
		Product p = MyBeanUtils.populate(Product.class, req.getParameterMap());
		ProductService productService = new ProductServiceImp();
		productService.delProduct(p);
		// 重定向到查询下架商品信息
		resp.sendRedirect(req.getContextPath() + "/AdminProductServlet?method=findPflag");

		return null;
	}

	// 上架商品
	public String add(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		// 调用业务层功能
		Product p = MyBeanUtils.populate(Product.class, req.getParameterMap());
		ProductService productService = new ProductServiceImp();
		productService.addProduct(p);
		// 重定向到商品信息
		resp.sendRedirect(req.getContextPath() + "/AdminProductServlet?method=findAllProductsWithPage&num=1");

		return null;
	}

	// 导入表格
	public void importExcel(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 携带表单中的数据向servcie,dao
		Product product = new Product();
		InputStream is = null;
		try {
			// 利用req.getInputStream();获取到请求体中全部数据,进行拆分和封装
			DiskFileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fac);
			List<FileItem> list = upload.parseRequest(req);
			// 4_遍历集合
			for (FileItem item : list) {
				if (item.isFormField()) {
					// 5_如果当前的FileItem对象是普通项
					// 将普通项上name属性的值作为键,将获取到的内容作为值,放入MAP中
					// {username<==>tom,password<==>1234}
					map.put(item.getFieldName(), item.getString("utf-8"));
				} else {
					// 6_如果当前的FileItem对象是上传项

					// 获取到原始的文件名称
					String oldFileName = item.getName();
					// 获取到要保存文件的名称 1222.doc 123421342143214.doc
					String newFileName = UploadUtils.getUUIDName(oldFileName);

					// 通过FileItem获取到输入流对象,通过输入流可以获取到图片二进制数据
					is = item.getInputStream();

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		ParseExcel parser = new ParseExcel();
		// InputStream is = new FileInputStream(map.get("pimage"));
		List<String[]> result = parser.parseExcel(is, "xlsx", 1);
		try {
			for (String[] temp : result) {
				String sql = "insert into product(pid,pname,market_price,shop_price,pimage,pdate,is_hot,pdesc,cid) VALUES (?,?,?,?,?,?,?,?,?)";

				QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
				qr.update(sql, temp);
				System.out.println(Arrays.toString(temp));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			resp.sendRedirect("/store_v5/AdminProductServlet?method=findAllProductsWithPage&num=1");
		}

		JOptionPane.showMessageDialog(null, "导入成功!");
		resp.sendRedirect("/store_v5/AdminProductServlet?method=findAllProductsWithPage&num=1");

	}

	// 导出数据到表格
	public void exportExcel(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 数据库查出所有商品集合 得到一个list

		List<Product> productsList = new ArrayList<Product>();
		String find = "select pid,pname,market_price,shop_price,pimage,pdate,is_hot,pdesc,cid from product";
		try {
			QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
			// Object[]
			// params={p.getPid(),p.getPname(),p.getMarket_price(),p.getShop_price(),p.getPdate(),p.getIs_hot(),p.getPdesc()
			// ,p.getCid()};
			productsList = qr.query(find, new BeanListHandler<>(Product.class));
			JOptionPane.showMessageDialog(null, "导出成功!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			ExportExcel export = new ExportExcel();
			OutputStream out = new FileOutputStream("D:/商品表.xls");
			export.exportExcelFile(productsList, out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resp.sendRedirect("/store_v5/AdminProductServlet?method=findAllProductsWithPage&num=1");

	}
}
