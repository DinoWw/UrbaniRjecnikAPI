package hr.dww.urt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;



/*
@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public User registerNewUserAccount(UserDto userDto) throws Exception {
        if (userNameExists(userDto.getEmail())) {
        	// TODO: impolement exception
            //throw new UserAlreadyExistException("There is an account with that email address: "+ userDto.getEmail());
        	throw new Exception("There is an account with that email address: "
                    + userDto.getEmail());
        }
        

        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setRole("ROLE_USER");
        
        return userRepository.save(user);

        
    }
    private boolean userNameExists(String userName) {
        return userRepository.findByUserName(userName) != null;
    }
}
*/