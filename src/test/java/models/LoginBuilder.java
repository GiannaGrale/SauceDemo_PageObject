package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginBuilder {
    private String login;
    private String password;
}

