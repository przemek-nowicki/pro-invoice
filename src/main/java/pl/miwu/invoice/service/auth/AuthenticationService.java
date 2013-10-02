package pl.miwu.invoice.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.miwu.invoice.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 16.09.13
 * Time: 17:01
 */
public class AuthenticationService implements UserDetailsService  {

    @Autowired
    private UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        pl.miwu.invoice.model.User user = userRepository.getByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User for username " + username + "was not found.");
        }
        if(user.getUserRole()==null){
            throw new UsernameNotFoundException(username + "has no permissions.");
        }
        return new UserDetailsAdapter(user);
    }
}
