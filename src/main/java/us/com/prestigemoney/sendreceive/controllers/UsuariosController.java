package us.com.prestigemoney.sendreceive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import us.com.prestigemoney.sendreceive.model.LoginModel;
import us.com.prestigemoney.sendreceive.repository.LoginRepository;
import us.com.prestigemoney.sendreceive.service.UsuariosService;

@Controller
public class UsuariosController {

    @Autowired
    private LoginRepository repository;

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping("/")
    public ModelAndView getLogin() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @GetMapping("/login-up")
    public ModelAndView getLoginUp() {
        ModelAndView mv = new ModelAndView("login-up");
        mv.addObject("loginModel", new LoginModel());
        return mv;
    }

    @PostMapping(value = "/saveUsuarios")
    public ModelAndView getLoginUpModel(LoginModel loginModel) throws Exception {
            ModelAndView mv = new ModelAndView();
            usuariosService.saveUsuarios(loginModel);
            mv.setViewName("redirect:/");
            return mv;
        }
    }

