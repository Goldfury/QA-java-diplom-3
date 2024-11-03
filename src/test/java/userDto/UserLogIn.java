package userDto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserLogIn {
    private String email;
    private String password;


}
