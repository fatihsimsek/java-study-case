package com.readingisgood.ordermanagement.security;

import com.readingisgood.ordermanagement.entity.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class UserPrincipal implements UserDetails {

    private String id;

    private String fullname;

    private String email;

    private String password;

    public UserPrincipal(String id, String fullname, String email, String password) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
    }

    public static UserPrincipal create(Customer customer) {
        return new UserPrincipal(
                customer.getId(),
                customer.getFullname(),
                customer.getEmail(),
                customer.getPassword()
        );
    }

    public String getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
