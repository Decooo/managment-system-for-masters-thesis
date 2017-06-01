package com.jakub.authentication;

import com.jakub.daoimpl.UsersDAOImpl;
import com.jakub.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakub on 26.05.2017.
 */
@Service
@Transactional
public class MyDBAuthenticationService implements UserDetailsService {


    private UsersDAOImpl userDAOImpl = new UsersDAOImpl();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userDAOImpl.findUser(username);

        if (user == null) {
            throw new UsernameNotFoundException("Users " + username + " was not found in the database");
        }


        String role = user.getRola();
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+role);

        grantList.add(grantedAuthority);

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        UserDetails userDetails = (UserDetails) new User(user.getLogin(), user.getHaslo(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantList);
        return userDetails;

    }
}
