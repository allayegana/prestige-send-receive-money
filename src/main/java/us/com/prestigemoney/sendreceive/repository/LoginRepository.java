package us.com.prestigemoney.sendreceive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import us.com.prestigemoney.sendreceive.model.LoginModel;

import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<LoginModel,Integer> {

    LoginModel findByMotpass(String motpass);

    @Query(value = "SELECT i FROM USUARIO i WHERE i.email =:email", nativeQuery = true)
    List<LoginModel> findByEmail(String email);
}
