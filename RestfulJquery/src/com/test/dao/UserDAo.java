package com.test.dao;

import java.util.List;

import com.test.model.UserModel;

public interface UserDAo {

	public List<UserModel> getAll();

	public boolean addUser(UserModel model);

	public boolean updateUser(UserModel model);

	public boolean deleteUser(int id);

	public UserModel findById(int id);

}
