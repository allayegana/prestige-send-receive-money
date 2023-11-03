package us.com.prestigemoney.sendreceive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import us.com.prestigemoney.sendreceive.exception.UserServicoException;
import us.com.prestigemoney.sendreceive.model.LoginModel;
import us.com.prestigemoney.sendreceive.model.SenderData;
import us.com.prestigemoney.sendreceive.repository.LoginRepository;
import us.com.prestigemoney.sendreceive.service.UsuariosService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;

@Controller
public class UsuariosController {

    @Autowired
    private LoginRepository repository;

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping("/")
    public ModelAndView getLogin() {
        ModelAndView mv = new ModelAndView("login");
       mv.addObject("loginModel", new LoginModel());
        return mv;
    }

    @GetMapping("/prestige-principal")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("prestige-principal");
        mv.addObject("senderData",new SenderData());
        return mv;
    }

    @GetMapping("/login-up")
    public ModelAndView getLoginUp() {
        ModelAndView mv = new ModelAndView("login-up");
        mv.addObject("loginModel", new LoginModel());
        return mv;
    }

    @RequestMapping(value = "/saveUsuarios",method = RequestMethod.POST)
    public ModelAndView getLoginUpModel(@Valid LoginModel loginModel) throws Exception {
        ModelAndView mv = new ModelAndView();
        if (repository.findByEmail(loginModel.getEmail()) != null){
            mv.setViewName("redirect:/login-up");
        }else {
            repository.save(loginModel);
            mv.setViewName("redirect:/");
        }
        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@Valid LoginModel loginModel, BindingResult br, HttpSession session) throws ParseException, UserServicoException {
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("loginModel", new LoginModel());
        if (br.hasErrors()) {
           return mv;
        }
        LoginModel userLogin = usuariosService.UserLogin(loginModel.getUser(), loginModel.getSenha());
        if (userLogin == null) {
            mv.addObject("msg", "this user is not match");
        } else {
            session.setAttribute("userLogin", userLogin);
            return index();
        }
        return mv;
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session) throws Exception {
          session.invalidate();
         session.setMaxInactiveInterval(30);
        return getLogin();
    }

}



