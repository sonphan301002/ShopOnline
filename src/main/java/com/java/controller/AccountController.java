package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.entity.Account;
import com.java.service.AccountService;

@Controller
public class AccountController {
	@Autowired
	AccountService accountService;

	/* LOGIN */
	@RequestMapping("/account/login/form")
	public String form(Model model) {
		model.addAttribute("message", "Vui lòng đăng nhập!");

		return "account/login";
	}

	@RequestMapping("/account/login/success")
	public String success() {

		return "redirect:/index";
	}

	@RequestMapping("/account/login/error")
	public String error(Model model) {
		model.addAttribute("message", "Sai tên đăng nhập hoặc mật khẩu!");

		return "account/login";
	}

	@RequestMapping("/account/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("message", "Không có quyền truy xuất!");

		return "account/login";
	}
	/* LOGIN-END */

	/* REGISTER */
	@RequestMapping("/account/register/form")
	public String form() {
		return "account/register";
	}

	@RequestMapping("/account/register")
	public String register(Model model, Account account) {
		accountService.save(account);

		return "redirect:/account/login/form";
	}
	/* REGISTER-END */

	/* LOGOFF */
	@RequestMapping("/account/logoff/success")
	public String logoff(Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất!");

		return "account/login";
	}
	/* LOGOFF-END */

	/* INFO */
	@RequestMapping("/account/info")
	public String info(Model model) {

		return "account/info";
	}

	@RequestMapping("/account/info/update/{id}")
	public String update(Model model, @PathVariable("id") String username) {
		model.addAttribute("accUpdate", accountService.findById(username));

		return "account/info";
	}

	@RequestMapping("/account/info/change/{username}")
	public String change(Model model, @PathVariable("username") String username,
			@RequestParam("currentPassword") String currentPassword, @RequestParam("newPassword") String newPassword,
			@RequestParam("confirmPassword") String confirmPassword) {
		
		Account account = accountService.findById(username);
		if (account != null) {
			if (!currentPassword.equals(account.getPassword())) {
				model.addAttribute("message", "Mật khẩu hiện tại không đúng!");
			} else if (!confirmPassword.equals(newPassword)) {
				model.addAttribute("message", "Mật khẩu không trùng khớp!");
			} else {
				account.setPassword(newPassword);
				accountService.save(account);
			}
		}

		return "account/info";
	}
	/* INFO-END */
}
