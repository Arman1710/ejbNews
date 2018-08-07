package epam.news.util;

import epam.news.exception.InvalidInputDataException;
import epam.news.model.dto.UserDTO;
import epam.news.services.UserService;
import org.apache.log4j.Logger;

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

    public Set<String> validateUser(UserDTO userDTO) throws InvalidInputDataException{
        Set<String> errors = new HashSet<>();

        if (userDTO.getUsername().length() < 8 || userDTO.getUsername().length() > 32) {
            errors.add("usernameSize");
            throw new InvalidInputDataException("username is too short (8) or too long (32)");
        }

        if (userDTO.getPassword().length() < 8 || userDTO.getPassword().length() > 32) {
            errors.add("passwordSize");
            throw new InvalidInputDataException("password is too short (8) or too long (32)");
        }
        if (!userDTO.getConfirmPassword().equals(userDTO.getPassword())) {
            errors.add("passwordDifferent");
            throw new InvalidInputDataException("different confirm password");
        }
        if (userService.getUserByUsername(userDTO.getUsername()) != null) {
            errors.add("usernameDuplicate");
            throw new InvalidInputDataException("username is already exists");
        }
        return errors;
    }
}
