package core;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.Map;

public class TokenManager {

    private static String TOKEN;

    public static String getToken() {
        if (TOKEN == null) {
            Map<String, String> login = new HashMap<>();
            login.put("email", Config.getUserEmail());
            login.put("senha", Config.getUserPassword());

            TOKEN = RestAssured
                    .given()
                    .baseUri(Config.getBaseUrl())
                    .port(Config.getPort())
                    .contentType(ContentType.JSON)
                    .body(login)
                    .when()
                    .post("/signin")
                    .then()
                    .statusCode(200)
                    .extract().path("token");
        }
        return TOKEN;
    }
}