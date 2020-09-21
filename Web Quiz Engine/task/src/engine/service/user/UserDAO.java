package engine.service.user;

import engine.dto.user.UserDTO;
import engine.exception.NoSuchUserException;
import engine.exception.UserAlreadyExistsException;

import java.util.List;

public interface UserDAO {

    UserDTO createUser(UserDTO userDTO) throws UserAlreadyExistsException;
    UserDTO getByEmail(String email) throws NoSuchUserException;
    List<UserDTO> getAll();

}
