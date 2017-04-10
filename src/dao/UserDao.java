package dao;

import java.util.List;

import domain.User;

public interface UserDao {
	
	int save(User u);

	User getUserByPhone(String phoneNum);
	
	List<User> getAllUser();
}
