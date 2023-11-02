package us.com.prestigemoney.sendreceive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import us.com.prestigemoney.sendreceive.model.LoginModel;

@Repository
public interface LoginRepository extends JpaRepository<LoginModel,Integer> {

    @Query(value = "SELECT * FROM USUARIO  WHERE email =:email", nativeQuery = true)
    LoginModel findByEmail(String email);

    public LoginModel findByUserAndSenha(String user, String senha);

}
