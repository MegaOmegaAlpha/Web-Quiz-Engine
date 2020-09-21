package engine.service.user;

import engine.dto.user.UserDTO;
import engine.exception.NoSuchUserException;
import engine.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO registerUser(UserDTO userDTO) throws UserAlreadyExistsException {
        String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encryptedPassword);
        return userDAO.createUser(userDTO);
    }

    public boolean isUserRegistered(UserDTO userDTO) throws NoSuchUserException {
        UserDTO userFromDB = userDAO.getByEmail(userDTO.getEmail());
        return passwordEncoder.matches(userDTO.getPassword(), userFromDB.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userDAO.getByEmail(username);
        } catch (NoSuchUserException e) {
            throw new UsernameNotFoundException("No user with such email", e);
        }
    }
}
