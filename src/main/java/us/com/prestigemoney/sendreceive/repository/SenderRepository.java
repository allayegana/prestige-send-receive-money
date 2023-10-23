package us.com.prestigemoney.sendreceive.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.com.prestigemoney.sendreceive.model.SenderData;

import java.util.Optional;

@Repository
public interface SenderRepository extends JpaRepository<SenderData,Long> {

    Optional<SenderData> findById(Long id);

    SenderData findByMatriculation(int matriculation);
}
