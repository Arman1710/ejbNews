package epam.news.util;

import epam.news.model.dto.UserDTO;
import epam.news.services.UserService;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@Stateless
@Local
public class UserValidator {

    @Inject
    private UserService userService;

    public Set<String> validateUser (UserDTO userDTO) {
        Set<String> errors = new HashSet<>();

        if (userDTO.getUsername().length() < 8 || userDTO.getUsername().length() > 32) {
            errors.add("usernameSize");
        }
        if (userDTO.getPassword().length() < 8 || userDTO.getPassword().length() > 32) {
            errors.add("passwordSize");
        }
        if (!userDTO.getConfirmPassword().equals(userDTO.getPassword())){
            errors.add("passwordDifferent");
        }
        if (userService.getUserByUsername(userDTO.getUsername()) != null){
            errors.add("usernameDuplicate");
        }
        return errors;
 }
}
