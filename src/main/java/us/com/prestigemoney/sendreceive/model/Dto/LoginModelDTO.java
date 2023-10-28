package us.com.prestigemoney.sendreceive.model.Dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginModelDTO {
    @NotNull
    private String usuario;
    @NotNull
    private String motpass;
};


