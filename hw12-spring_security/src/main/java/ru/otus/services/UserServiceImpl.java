package ru.otus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.entities.User;
import ru.otus.principal.UserPrincipal;
import ru.otus.repositories.UserRepository;

/*
 * @created 13/12 - otus-spring
 * @author Ilya Rogatkin
 */
@RequiredArgsConstructor
@Service("userServiceImpl")
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  this.userRepository.findByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException(String.format("%s%s%s", "User with name: ", username, "not found"));
        });
        return new UserPrincipal(user);
    }
}
