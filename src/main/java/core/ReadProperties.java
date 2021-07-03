package core;

import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    protected Properties properties;

    public ReadProperties() {
        properties = new Properties();

        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getURL() { return properties.getProperty("url");}
    public String getBrowser() { return properties.getProperty("browser");}
    public String getUsername() { return properties.getProperty("usernameStandard");}
    public String getProblemUsername() { return properties.getProperty("usernameProblem");}
    public String getGlitchUsername() { return properties.getProperty("usernameGlitch");}
    public String getLockedUsername() { return properties.getProperty("usernameLocked");}
    public String getRandomCharsUsername() { return properties.getProperty("usernameRandomChars");}

    public String getPassword() { return properties.getProperty("password");}
    public int getTimeout(){ return Integer.parseInt(properties.getProperty("timeout"));}
    public boolean getHeadless(){ return Boolean.parseBoolean(properties.getProperty("headless"));}

}