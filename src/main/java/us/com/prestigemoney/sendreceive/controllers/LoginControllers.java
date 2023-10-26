package us.com.prestigemoney.sendreceive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import us.com.prestigemoney.sendreceive.model.LoginModel;
import us.com.prestigemoney.sendreceive.repository.LoginRepository;

import javax.validation.Valid;
import java.text.ParseException;

@Controller
public class LoginControllers {

    @Autowired
    private LoginRepository repository;

    @GetMapping("/login")
    public ModelAndView getLoginModel(@Valid LoginModel loginModel) {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView Sauvegarder(@Valid LoginModel loginModel, BindingResult result, RedirectAttributes attributes) throws ParseException {
        if (result.hasErrors()) {
            return getLoginModel(loginModel);
        } else {
            ModelAndView mv = new ModelAndView("redirect:/index-principal");

            repository.save(loginModel);
            attributes.addFlashAttribute("mensagem", " singn with succes");
            return mv;
        }
    }
}
