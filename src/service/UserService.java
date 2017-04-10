package service;

import java.util.List;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;

public class UserService {
	
	private UserDao ud = new UserDaoImpl();
	private static final int USER_HAS_EXISTED = 1;
	private static final int USER_HAS_UNREGISTER = 2;
	private static final int LOGIN_SUCCESS =  0;
	private static final int PASSWORD_ERROR = 1;
	
	public int register(User u){
		int result;
		User existU = ud.getUserByPhone(u.getPhoneNum());
		if(existU != null){
			result = USER_HAS_EXISTED;
		}else{			
			result = ud.save(u);
		}
		return result;
	}
	
	
	public int login(User u){
		User existU = ud.getUserByPhone(u.getPhoneNum());
		if(existU == null){
			return USER_HAS_UNREGISTER;
		}
	
		if(!existU.getPassword().equals(u.getPassword())){
			return PASSWORD_ERROR;
		}
		return LOGIN_SUCCESS;
	}
	
	public List<User> getAllUser(){
		return ud.getAllUser();
	}
	
	public boolean userIfRegistered(String phoneNum){
		
		User user = ud.getUserByPhone(phoneNum);
		
		if(user != null){
			return true;  
		}else{
			return false; 
		}
	}
}
