package us.com.prestigemoney.sendreceive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import us.com.prestigemoney.sendreceive.exception.UserServicoException;
import us.com.prestigemoney.sendreceive.model.LoginModel;
import us.com.prestigemoney.sendreceive.model.SenderData;
import us.com.prestigemoney.sendreceive.repository.LoginRepository;
import us.com.prestigemoney.sendreceive.repository.SenderRepository;
import us.com.prestigemoney.sendreceive.service.UsuariosService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Objects;

@Controller
public class UsuariosController {

    @Autowired
    private LoginRepository repository;

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private SenderRepository senderRepository;

    private final String agence = "POSSRMNY";

    @GetMapping("/")
    public ModelAndView getLogin() {
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("loginModel", new LoginModel());
        return mv;
    }

    @GetMapping("/prestige-principal")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("prestige-principal");
        mv.addObject("senderData", new SenderData());
        return mv;
    }


    @GetMapping("/login-up")
    public ModelAndView getLoginUp() {
        ModelAndView mv = new ModelAndView("login-up");
        mv.addObject("loginModel", new LoginModel());
        return mv;
    }


    @RequestMapping(value = "/saveUsuarios", method = RequestMethod.POST)
    public ModelAndView getLoginUpModel(@Valid LoginModel loginModel, RedirectAttributes attributes) throws Exception {
        ModelAndView mv = new ModelAndView();
        if (repository.findByEmail(loginModel.getEmail()) != null || !Objects.equals(loginModel.getAgence(), agence)) {
            mv.setViewName("redirect:/login-up");
            attributes.addFlashAttribute("msg", "this: " + loginModel.getEmail() + " have account or agence " +
                    " " + loginModel.getAgence() + " no existe" );
        } else {
            loginModel.setAgence("POSSRMNY");
            loginModel.setAtivo(true);
            repository.save(loginModel);
            mv.setViewName("redirect:/");
        }
        return mv;
    }

    @GetMapping("/invoice-prestige-total-login")
    public ModelAndView invoices() {
        ModelAndView mv = new ModelAndView("invoice-prestige-total-login");
        Sort sort = Sort.by("id").ascending();
        Pageable page = PageRequest.of(0, 100, sort);
        Page<LoginModel> LoginModel = repository.findAll(page);
        mv.addObject("LoginModel", LoginModel);
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
        if (userLogin == null || userLogin.getAtivo().equals(false)) {
            mv.addObject("msg", "this: " + loginModel.getUser() +" or " + loginModel.getSenha() + " is invalid or your account NO ACTIVE");
        } else {
            session.setAttribute("userLogin", userLogin);
            return index();
        }
        return mv;
    }

    @GetMapping("/atualizar-e-login-user/{id}")
    public String bloque(@PathVariable("id") Integer id) {
        LoginModel loginModel = repository.getReferenceById(id);
        if (loginModel.getAtivo().equals(false) || loginModel.getAtivo().equals(null) ){
            loginModel.setAtivo(true);
        }else {
            loginModel.setAtivo(false);
        }
        repository.save(loginModel);
        return "redirect:/invoice-prestige-total-login";
    }

    @GetMapping("/eliminar-e-login-user/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        repository.deleteById(id);
        return "redirect:/invoice-prestige-total-login";
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session) throws Exception {
        session.invalidate();
        session.setMaxInactiveInterval(10*600);
        return getLogin();
    }

}



