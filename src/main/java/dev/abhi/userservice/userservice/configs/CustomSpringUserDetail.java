package dev.abhi.userservice.userservice.configs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.abhi.userservice.userservice.models.Role;
import dev.abhi.userservice.userservice.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@JsonDeserialize(as = CustomSpringUserDetail.class)
@NoArgsConstructor
public class CustomSpringUserDetail implements UserDetails {

    private User user ;
    public CustomSpringUserDetail(User user){
        this.user = user ;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<CustomSpringGrantedAuthority> grantedAuthorities = new ArrayList<>();

        for(Role role : user.getRoles()){
            grantedAuthorities.add(new CustomSpringGrantedAuthority(role));
        }
        return grantedAuthorities ;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return user.getPassword() ;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true ;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true ;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}