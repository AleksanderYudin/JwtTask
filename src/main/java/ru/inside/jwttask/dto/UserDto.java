package ru.inside.jwttask.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.inside.jwttask.model.Role;
import ru.inside.jwttask.model.User;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Role role;

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if(role != null)
            user.setRole(role);
        else user.setRole(Role.ROLE_USER);
        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
