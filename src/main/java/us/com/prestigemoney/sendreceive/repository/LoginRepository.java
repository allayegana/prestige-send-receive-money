package us.com.prestigemoney.sendreceive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.com.prestigemoney.sendreceive.model.LoginModel;

@Repository
public interface LoginRepository extends JpaRepository<LoginModel,Long> {
}
