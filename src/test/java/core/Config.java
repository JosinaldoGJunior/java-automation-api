package core;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.http.ContentType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Properties properties = new Properties();
    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .systemProperties()
            .load();

    // Carrega as propriedades do arquivo config.properties, se ele existir.
    static {
        try (InputStream input = Config.class.getResourceAsStream("/config.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            System.err.println("Não foi possível carregar o config.properties, usando valores padrão. Erro: " + e.getMessage());
        }
    }

    // Cada método getter agora define sua própria hierarquia de forma explícita.
    // Hierarquia: Variável de Ambiente/ .env > config.properties > Valor Padrão no Código

    public static String getBaseUrl() {
        return dotenv.get("BASE_URL", properties.getProperty("base.url", "https://barrigarest.wcaquino.me"));
    }

    public static int getPort() {
        return Integer.parseInt(dotenv.get("PORT", properties.getProperty("port", "443")));
    }

    public static String getBasePath() {
        return dotenv.get("BASE_PATH", properties.getProperty("base.path", "/"));
    }

    public static ContentType getContentType() {
        return ContentType.valueOf(dotenv.get("CONTENT_TYPE", properties.getProperty("content.type", "JSON")).toUpperCase());
    }

    public static long getMaxTimeout() {
        return Long.parseLong(dotenv.get("MAX_TIMEOUT", properties.getProperty("max.timeout", "5000")));
    }

    // --- MÉTODOS CORRIGIDOS E EXPLÍCITOS PARA CREDENCIAIS ---

    public static String getUserEmail() {
        // Procura pela variável 'API_USER_EMAIL' no ambiente/.env, depois 'user.email' no arquivo, e por último usa o default.
        return dotenv.get("API_USER_EMAIL", properties.getProperty("user.email", "default.email@example.com"));
    }

    public static String getUserPassword() {
        // Procura pela variável 'API_USER_PASSWORD' no ambiente/.env, depois 'user.password' no arquivo, e por último usa o default.
        return dotenv.get("API_USER_PASSWORD", properties.getProperty("user.password", "defaultpassword"));
    }
}