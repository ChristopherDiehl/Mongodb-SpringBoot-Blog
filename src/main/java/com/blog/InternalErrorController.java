package com.blog;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InternalErrorController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error(HttpServletRequest request, Model model) {
    	model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		String errorMessage = null;

		if (throwable != null) 
		{
			errorMessage = throwable.getMessage();
		}

		model.addAttribute("errorMessage", errorMessage);
		return "error";
    }

	@Override
	public String getErrorPath() {
		return PATH;
	}
	
	@RequestMapping("/login-error")
	public String loginError(Model model) 
	{
		System.out.println("ADDING LOGING ERROR");
		model.addAttribute("loginError", true);
		return "login";
	}
	

}
