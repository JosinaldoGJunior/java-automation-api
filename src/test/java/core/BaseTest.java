package core;

import clients.AccountsClient;
import clients.ResetClient;
import clients.TransactionsClient;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import org.junit.jupiter.api.BeforeAll;

import static org.hamcrest.Matchers.lessThan;

public class BaseTest {

    protected static AccountsClient accountsClient;
    protected static TransactionsClient transactionsClient;
    protected static ResetClient resetClient;
    protected static String token;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = Config.getBaseUrl();
        RestAssured.port = Config.getPort();

        token = TokenManager.getToken();

        accountsClient = new AccountsClient();
        transactionsClient = new TransactionsClient();
        resetClient = new ResetClient();

        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectResponseTime(lessThan(Config.getMaxTimeout()));
        RestAssured.responseSpecification = resBuilder.build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}