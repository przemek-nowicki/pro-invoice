package pl.miwu.invoice.service.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 19.09.13
 * Time: 15:32
 */

public class UserDetailsAdapter extends User {
    private final int id;
    private final boolean accountNonExpired = true;
    private final boolean credentialsNonExpired = true;
    private final boolean accountNonLocked = true;

    public UserDetailsAdapter(pl.miwu.invoice.model.User userEntity) {
        super(userEntity.getUsername(), userEntity.getPassword(), userEntity.isEnabled(), true, true, true, Arrays.asList(new SimpleGrantedAuthority(userEntity.getUserRole().getName())));
        this.id = userEntity.getId();
    }

    public int getId() {
        return id;
    }
}
