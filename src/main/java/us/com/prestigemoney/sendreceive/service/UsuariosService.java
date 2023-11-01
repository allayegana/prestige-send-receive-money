package us.com.prestigemoney.sendreceive.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.com.prestigemoney.sendreceive.Utils.Ultil;
import us.com.prestigemoney.sendreceive.exception.EmailExistesException;
import us.com.prestigemoney.sendreceive.exception.ErroCrytografia;
import us.com.prestigemoney.sendreceive.model.LoginModel;
import us.com.prestigemoney.sendreceive.repository.LoginRepository;

@Service
public class UsuariosService {

    @Autowired
    private LoginRepository repository;
    public void saveUsuarios(LoginModel loginModel) throws Exception{


        try {
            if (repository.findByEmail(loginModel.getEmail()) != null){
                throw new EmailExistesException("this email is exist : " + loginModel.getEmail());
            }

            loginModel.setMotpass(Ultil.MD5(loginModel.getMotpass()));

        }catch (Exception e){
             throw new ErroCrytografia(" error de ruptographia de mot de passe " + e.getMessage());
        }

        repository.save(loginModel);
    }


}
