package models;

public class Login {
    private String login;
    private String password;

    public Login(Builder builder) {
        this.login = builder.login;
        this.password = builder.password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public static final class Builder {
        private String login;
        private String password;

        public Builder() {
        }

        public Builder(Login origin) {
            this.login = origin.login;
            this.password = origin.password;
        }

        public Builder withLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Login build() {
            return new Login(this);
        }
    }
}

