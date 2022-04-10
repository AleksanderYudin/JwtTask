package ru.inside.jwttask.security.jwt;


import ru.inside.jwttask.model.User;

public final class JwtUserFactory {

    public static JwtUser create(User user){
        return new JwtUser(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole());
    }
}
