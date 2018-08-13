package zl.example.login.dao;

import org.springframework.stereotype.Repository;

import zl.example.login.pojo.User;

@Repository
public interface UserDAO {
	public User loginByNameAndPass(String uname,String pass);
}
