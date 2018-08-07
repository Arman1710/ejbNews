package epam.news.services.impl;

import epam.news.converter.UserConverter;
import epam.news.dao.UserDAO;
import epam.news.model.dto.UserDTO;
import epam.news.model.entity.User;
import epam.news.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserServiceImpl implements UserService {
    private final static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Inject
    private UserConverter userConverter;

    @Inject
    private UserDAO userDAO;

    public User createUser(UserDTO userDTO) {
        LOGGER.info("creating user");
        User user = userConverter.DTOToEntity(userDTO);
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        user.setRoleId(2L);
        User createdUser = userDAO.create(user);
        LOGGER.info("User is created: " + user);
        return createdUser;
    }

    public User getUserByUsername(String username) {
        return userDAO.getByUserName(username);
    }
}





