package zl.example.login.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zl.example.login.dao.UserDAO;
import zl.example.login.pojo.User;
import zl.example.login.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	public User loginByNameAndPass(String uname, String pass) {
		return userDAO.loginByNameAndPass(uname, pass);
	}

}
