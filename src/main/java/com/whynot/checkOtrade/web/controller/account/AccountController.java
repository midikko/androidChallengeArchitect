package com.whynot.checkOtrade.web.controller.account;

import com.whynot.checkOtrade.web.controller.signup.SignupForm;
import com.whynot.checkOtrade.web.dao.AccountRepository;
import com.whynot.checkOtrade.web.domain.Account;
import com.whynot.checkOtrade.web.support.web.MessageHelper;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Secured("ROLE_USER")
class AccountController {

	@Autowired
	private AccountRepository accountRepository;

	public AccountController() {
	}

	@RequestMapping(value = "account/info", method = RequestMethod.GET)
	public String accounts(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Account account = accountRepository.findByEmail(name);
		
		model.addAttribute("account", account);
		model.addAttribute("accountInfo", new AccountInfo());
		return "account/info";
	}

	@RequestMapping(value = "account", method = RequestMethod.GET)
	public String accounts1(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Account account = accountRepository.findByEmail(name);
		
		model.addAttribute("account", account);
		model.addAttribute("accountInfo", new AccountInfo());
		return "account/info";
	}
	
	@RequestMapping(value = "account/info", method = RequestMethod.POST)
	public String accounts(@Valid @ModelAttribute AccountInfo accountInfo, Errors errors, RedirectAttributes ra) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Account account = accountRepository.findByEmail(name);
		
		System.out.print(name);
		
		account.setName(accountInfo.getName());
		account.setPassword(accountInfo.getPassword());
		System.out.println("\n" + account.getEmail() + "  ::  " + account.getName() + "  ::  " + account.getPassword() + "  ::  " + account.getAvatar());
		accountRepository.update(account);
        MessageHelper.addSuccessAttribute(ra, "signup.success");
		return "redirect:/";
	}
}
