package zl.example.login.service;

import zl.example.login.pojo.User;

public interface UserService {
	public User loginByNameAndPass(String uname,String pass);
}
