package ru.seppna.sportwebshop_rest.security;

import org.springframework.security.core.Authentication;
import ru.seppna.sportwebshop_rest.models.User;
import ru.seppna.sportwebshop_rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    private final UserRepository repository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username).orElseThrow();
        return SecurityUser.fromUser(user);
    }

    //Authentication-информация о текущем Usere
    public boolean hasUserId(Authentication authentication, int userId) {
        User user = repository.findByEmail(authentication.getName()).orElseThrow();
        return user.getId() == userId;
    }
}
