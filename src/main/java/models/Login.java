package models;

public class Login {
    private String login;
    private String password;
    private String randomCharsUser;
    private String lockedUser;
    private String glitchUser;
    private String problemUser;

    private Login() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRandomCharsUser() {
        return randomCharsUser;
    }

    public String getLockedUser() {
        return lockedUser;
    }

    public String getGlitchUser() {
        return glitchUser;
    }

    public String getProblemUser() {
        return problemUser;
    }

    public static Builder newBuilder() {
        return new Login().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder withLogin(String login) {
            Login.this.login = login;
            return this;
        }

        public Builder withPassword(String password) {
            Login.this.password = password;
            return this;
        }
        public Builder withRandomCharsLogin(String randomCharsUser) {
            Login.this.randomCharsUser = randomCharsUser;
            return this;
        }

        public Builder withLockedLogin(String lockedUser) {
            Login.this.lockedUser = lockedUser;
            return this;
        }

        public Builder withGlitchLogin(String glitchUser) {
            Login.this.glitchUser = glitchUser;
            return this;
        }

        public Builder withProblemLogin(String problemUser) {
            Login.this.problemUser = problemUser;
            return this;
        }

        public Login build() {
            return Login. this;
        }
    }
}

