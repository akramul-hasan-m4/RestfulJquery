package com.test.daoImpl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.test.dao.UserDAo;
import com.test.model.UserModel;

@SuppressWarnings("deprecation")
@Repository
public class UserDaoImpl implements UserDAo {
	
	@Autowired
	UserModel userModel;
    @Autowired
	static Date date =new Date();	
	private static ArrayList<UserModel> list;
	//static String[] li = new String[] { new String("Dhaka"),new String("ctg") };

	static {
		ArrayList<UserModel> userlist = new ArrayList<>();
		UserModel user = null;
		for (int i = 1; i <= 5; i++) {
			user = new UserModel(i, "firstName " + i, "LastName " + i, "email"+i+"@abc.com ", "password " + i,
					"District " + i, "0191"  + i, "Male", "cricket", "islam", date);
			userlist.add(user);
		}
		list = userlist;
	}

	@Override
	public List<UserModel> getAll() {
		return list;
	}

	@Override
	public boolean addUser(UserModel model) {
		list.add(model);
		
		return true;
	}

	@Override
	public boolean updateUser(UserModel model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(int id) {
		ArrayList<UserModel> listt = new ArrayList<>();
		for (UserModel user : list) {
			if (user.getId() == id) {
				listt.add(user);
			}
		}
		list.removeAll(listt);
		return true;
	}

	@Override
	public UserModel findById(int id) {
		UserModel user=new UserModel();
		for (UserModel userModel : list) {
			if(userModel.getId()==id) {
				user=userModel;
			}
		}
		return user;
	}

}
