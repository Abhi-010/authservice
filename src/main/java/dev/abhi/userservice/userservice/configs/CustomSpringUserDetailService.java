package dev.abhi.userservice.userservice.configs;

import dev.abhi.userservice.userservice.models.User;
import dev.abhi.userservice.userservice.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomSpringUserDetailService implements UserDetailsService {

    private UserRepository userRepository ;
    public CustomSpringUserDetailService(UserRepository userRepository){
        this.userRepository = userRepository ;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username) ;
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User doesn't exist");
        }
        User user = optionalUser.get() ;
        return new CustomSpringUserDetail(user);
    }
}