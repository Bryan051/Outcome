package com.sparta.outcome.security;

import com.sparta.outcome.domain.User;
import com.sparta.outcome.domain.read.UserReadRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserReadRepository userReadRepository;

    public UserDetailsServiceImpl(UserReadRepository userReadRepository) {
        this.userReadRepository = userReadRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userReadRepository.findByUserName(userName).orElseThrow(
                () -> new UsernameNotFoundException("Not Found" + userName)
        );
        return new UserDetailsImpl(user);
    }
}