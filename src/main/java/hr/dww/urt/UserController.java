package hr.dww.urt;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


/*
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/registration")
	public ModelAndView registerUserAccount(
	  @ModelAttribute("user") @Valid UserDto userDto,
	  HttpServletRequest request,
	  Errors errors) {
	    
	    try {
	        User registered = userService.registerNewUserAccount(userDto);
	    } catch (Exception uaeEx) {
	    	ModelAndView mav = new ModelAndView("UserAleradyExistsException");
	        mav.addObject("message", "An account for that username/email already exists.");
	        return mav;
	    }

	    return new ModelAndView("successRegister", "user", userDto);
	}
}

*/