package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginBuilder {
    private String login;
    private String password;
    private String randomCharsUser;
    private String lockedUser;
    private String glitchUser;
    private String problemUser;
}

