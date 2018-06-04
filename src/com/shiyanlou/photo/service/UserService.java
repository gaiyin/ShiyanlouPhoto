package com.shiyanlou.photo.service;

import java.util.List;

import com.shiyanlou.photo.domain.User;
import com.shiyanlou.photo.util.DBUtils;

/**
 * �û�������
 * @author JXS
 *
 */
public class UserService {
	/**
	 * ͨ���û�����ȡ�û�
	 * @param username �û���
	 * @return �û�
	 */
	public User getUserByUsername(String username){
		String sql="select id,username,password from user where username = ?";
		String[] parameters = {username};
		List<Object[]> users = DBUtils.query(sql, parameters);
		if(users.size() == 0){
			return null;
		}else{
			Object[] objects = users.get(0);
			return objects == null ? null : new User(Integer.parseInt(objects[0].toString()),objects[1].toString(),objects[2].toString(),null);
		}
	}
	
	/**
	 * ����û�
	 * @param user �û�
	 */
	public void addUser(User user){
		String[] sqls = {"insert into user(username,password) values(?,?)"};
		String[][] parameters = {{user.getUsername(),user.getPassword()}};
		DBUtils.updates(sqls, parameters);
	}
}
