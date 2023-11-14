package us.com.prestigemoney.sendreceive.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import us.com.prestigemoney.sendreceive.model.SenderData;

import java.util.List;

@Repository
public interface SenderRepository extends JpaRepository<SenderData,Integer> {

    List<SenderData> findByTelephoneContainingIgnoreCase(String telephone);

    @Query(value = "SELECT * FROM CUSTOMER  WHERE status ='unpaid'", nativeQuery = true)
    List<SenderData> findByStatus();

    @Query(value = "SELECT COUNT(status) FROM CUSTOMER  WHERE status ='unpaid'", nativeQuery = true)
    Long findByStatusUnpaid();

    @Query(value = "SELECT * FROM CUSTOMER  WHERE pays ='senegal'", nativeQuery = true)
    List<SenderData> findByPays();

    @Query(value = "SELECT COUNT(pays) FROM CUSTOMER where pays = 'senegal'", nativeQuery = true)
    Long findByPaysSenegal();
    @Query(value = "SELECT * FROM CUSTOMER  WHERE pays ='mali'", nativeQuery = true)
    List<SenderData> findByPaysMl();

    @Query(value = "SELECT COUNT(pays) FROM CUSTOMER where pays = 'mali'", nativeQuery = true)
    Long findByPaysMALI();

    @Query(value = "SELECT * FROM CUSTOMER  WHERE pays ='COTE DIVOIRE'", nativeQuery = true)
    List<SenderData> findByPaysCiv();

    @Query(value = "SELECT COUNT(pays) FROM CUSTOMER where pays = 'COTE DIVOIRE'", nativeQuery = true)
    Long findByPaysCotedivoire();


    @Query(value = "SELECT sum(recevoir) FROM CUSTOMER  WHERE status ='paid'", nativeQuery = true)
    Integer findByRecevoir();

    @Query(value = "SELECT sum(montant) FROM CUSTOMER  WHERE status ='paid'", nativeQuery = true)
    Integer findByMontant();


}
