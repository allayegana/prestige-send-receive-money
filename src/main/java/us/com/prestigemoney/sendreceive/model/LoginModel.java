package us.com.prestigemoney.sendreceive.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USUARIO")
public class LoginModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @NotNull
    @Size(min = 5,max = 30)
    private String usuario;
    @NotNull
    @Size(min = 5,max = 30)
    private String motpass;
    @Email
    @NotNull
    private String email;

};


