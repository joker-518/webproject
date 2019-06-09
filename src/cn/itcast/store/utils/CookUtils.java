package cn.itcast.store.utils;



import javax.servlet.http.Cookie;

public class CookUtils {
	public static Cookie getCookieByName(String name, Cookie[] cookies) {
		if(cookies!=null){
			for (Cookie c : cookies) {
				//
				if(name.equals(c.getName())){
					//
					return c;
				}
			}
		}
		return null;
	}
}
