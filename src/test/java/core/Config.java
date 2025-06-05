package core;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.http.ContentType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();
    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    static {
        try (InputStream input = Config.class.getResourceAsStream("/config.properties")) {
            if (input != null) {
                props.load(input);
            }
        } catch (IOException e) {
            System.err.println("Failed to load config.properties: " + e.getMessage());
        }

        if (!props.containsKey("base.url")) {
            props.setProperty("base.url", "https://barrigarest.wcaquino.me");
        }
        if (!props.containsKey("port")) {
            props.setProperty("port", "443");
        }
        if (!props.containsKey("base.path")) {
            props.setProperty("base.path", "/");
        }
        if (!props.containsKey("content.type")) {
            props.setProperty("content.type", "JSON");
        }
        if (!props.containsKey("max.timeout")) {
            props.setProperty("max.timeout", "5000");
        }
        if (!props.containsKey("user.email")) {
            String email = dotenv.get("API_USER_EMAIL", System.getenv("API_USER_EMAIL") != null ? System.getenv("API_USER_EMAIL") : "default.email@example.com");
            props.setProperty("user.email", email);
        }
        if (!props.containsKey("user.password")) {
            String password = dotenv.get("API_USER_PASSWORD", System.getenv("API_USER_PASSWORD") != null ? System.getenv("API_USER_PASSWORD") : "defaultpassword");
            props.setProperty("user.password", password);
        }
    }

    public static String getBaseUrl() {
        return props.getProperty("base.url");
    }

    public static int getPort() {
        return Integer.parseInt(props.getProperty("port"));
    }

    public static String getBasePath() {
        return props.getProperty("base.path");
    }

    public static ContentType getContentType() {
        return ContentType.valueOf(props.getProperty("content.type").toUpperCase());
    }

    public static long getMaxTimeout() {
        return Long.parseLong(props.getProperty("max.timeout"));
    }

    public static String getUserEmail() {
        return props.getProperty("user.email");
    }

    public static String getUserPassword() {
        return props.getProperty("user.password");
    }
}
