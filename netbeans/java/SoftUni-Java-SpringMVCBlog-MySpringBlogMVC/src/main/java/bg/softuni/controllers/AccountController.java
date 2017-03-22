
package bg.softuni.controllers;

import bg.softuni.forms.LoginForm;
import bg.softuni.services.LoginService;
import bg.softuni.services.NotificationService;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {
    
    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private LoginService loginService;
    
    //тук GET
    @RequestMapping("/users/login")
    public String showLoginForm(LoginForm loginFrom){
        return "users/login";
    }
    
    //тук POST
    @RequestMapping(value="/users/login",method=RequestMethod.POST)
    public String showLoginForm(@Valid LoginForm loginFrom, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            notificationService.addErrorMessage("Моля въведи коректно формата");
            return "users/login";
        }
        if (loginService.authentice(loginFrom.getUsername(), loginFrom.getPassword())){
            notificationService.addErrorMessage("Нeкоректна формата");
            return "users/login";
        }
        notificationService.addInfoMessage("Login successful");
        return "redirect:/";
    }
}
