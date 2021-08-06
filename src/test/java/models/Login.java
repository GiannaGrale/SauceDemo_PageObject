package models;

public class Login {
    private String login;
    private String password;

    private Login() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
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

        public Login build() {
            return Login. this;
        }
    }
}

