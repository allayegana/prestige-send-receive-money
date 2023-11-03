package us.com.prestigemoney.sendreceive.model;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMER")
public class SenderData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @NotNull
    private String prenom;
    @NotNull
    @Column()
    @Size(max = 14, message = "check the phone number")
    private String telephone;

    @NotNull
    private Integer montant;
    @NotNull
    private String pays;
    @NotNull
    @Column(name = "nome")
    private String nome;
    @NotNull
    @Size(max = 14, message = "check the phone number")
    private String phone;

    @NotNull
    private Integer recevoir;
    @NotNull
    private String receiveTp;
    @NotNull
    private String status;
    @NotNull
    @Column(name ="matriculation" ,unique = true)
    private int matriculation;
    @Column(name = "jour")
    private String jour;
};

//create table CUSTOMER
//        (
//        Id BIGINT(20)primary key,
//        prenom varchar(255) not NULL,
//        telephone varchar(255) NOT NULL,
//        pays varchar(255) not NULL,
//        nome varchar(255) NOT NULL,
//        matriculation int(20) not Null,
//        jour varchar(255) NOT NULL
//        );
