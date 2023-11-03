package us.com.prestigemoney.sendreceive.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.com.prestigemoney.sendreceive.exception.UserServicoException;
import us.com.prestigemoney.sendreceive.model.LoginModel;
import us.com.prestigemoney.sendreceive.repository.LoginRepository;

@Service
public class UsuariosService {

    @Autowired
    private LoginRepository repository;

    public LoginModel UserLogin(String user, String senha) throws UserServicoException {

        LoginModel lg = repository.findByUserAndSenha(user, senha);

        return lg;
    }


}
