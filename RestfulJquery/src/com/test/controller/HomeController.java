package com.test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.dao.UserDAo;
import com.test.model.UserModel;

@Controller
public class HomeController {

	@Autowired
	UserDAo userdao;

	@RequestMapping("/")
	public String Home(Model m, UserModel user) {
		m.addAttribute("userlist", userdao.getAll());
		m.addAttribute("user1", new UserModel());
		return "index";
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("user1") UserModel user, BindingResult result, Model m) {
		if (result.hasErrors()) {
			m.addAttribute("userlist", userdao.getAll());
			System.out.println("==========" + result.toString());
			return "index";
		}

		boolean status = userdao.addUser(user);
		if (status) {
			m.addAttribute("userlist", userdao.getAll());
			m.addAttribute("sm", "User Created Successfully");
		}

		else {
			m.addAttribute("em", "user not created");
		}

		return "index";
	}

	@RequestMapping("/editUser/{id}")
	public String findbyIdf(Model m, @PathVariable("id") int id) {
		m.addAttribute("user", userdao.findById(id));
		m.addAttribute("user1", new UserModel());
		return "index";
	}

	@RequestMapping("/deleteUser/{id}")
	public String deleteuser(Model m, @PathVariable("id") int id) {
		boolean bool = userdao.deleteUser(id);
		UserModel um=new UserModel();
		um.setId(0);
		if (bool) {
			m.addAttribute("sm", "user deleted successfully");
			m.addAttribute("user1", um);
			m.addAttribute("userlist", userdao.getAll());
			return "index";
		} else {
			m.addAttribute("em", "user deleted failed");
			m.addAttribute("user1", new UserModel());
			m.addAttribute("userlist", userdao.getAll());
			return "index";
		}

	}
}
