package epam.news.services;

import epam.news.model.dto.UserDTO;
import epam.news.model.entity.User;

public interface UserService {
   boolean createUser(UserDTO userDTO);
   User getUserByUsername(String username);
}