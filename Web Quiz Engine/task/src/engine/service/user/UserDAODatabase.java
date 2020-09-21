package engine.service.user;

import engine.dto.user.UserDTO;
import engine.exception.NoSuchUserException;
import engine.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAODatabase implements UserDAO {

    private UserRepository userRepository;

    @Autowired
    public UserDAODatabase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) throws UserAlreadyExistsException {
        if (userRepository.existsById(userDTO.getEmail())) {
            throw new UserAlreadyExistsException();
        } else {
            return userRepository.save(userDTO);
        }
    }

    @Override
    public UserDTO getByEmail(String email) throws NoSuchUserException {
        return userRepository.findById(email).orElseThrow(NoSuchUserException::new);
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll();
    }
}
