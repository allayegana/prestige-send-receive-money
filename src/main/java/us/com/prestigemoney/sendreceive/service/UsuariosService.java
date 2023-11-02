package us.com.prestigemoney.sendreceive.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.com.prestigemoney.sendreceive.exception.EmailExistesException;
import us.com.prestigemoney.sendreceive.exception.UserServicoException;
import us.com.prestigemoney.sendreceive.model.LoginModel;
import us.com.prestigemoney.sendreceive.repository.LoginRepository;

@Service
public class UsuariosService {

    @Autowired
    private LoginRepository repository;
    public void saveUsuarios(LoginModel loginModel) throws Exception{


        try {
            if (repository.findByEmail(loginModel.getEmail()) != null){
                throw new EmailExistesException("this email is have account put your email : " + loginModel.getEmail());
            }

        //    loginModel.setSenha(Ultil.md5(loginModel.getSenha()));

        }catch (Exception e){
             throw new Exception(e);
        }

        repository.save(loginModel);
    }

    public LoginModel UserLogin(String user , String senha) throws UserServicoException {

        LoginModel lg = repository.findByUserAndSenha(user,senha);

        return lg;
    }


}
