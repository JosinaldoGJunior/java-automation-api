package test;

import core.BaseTest;
import io.restassured.response.Response;
import models.AccountPayload;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.PayloadGenerator;
import utils.TestDataSetup;

import static org.hamcrest.Matchers.*;

@DisplayName("Account Management Tests")
public class AccountTests extends BaseTest {

    @Test
    @DisplayName("Should create account successfully")
    void shouldCreateAccountSuccessfully() {
        // Arrange - A specific payload
        AccountPayload payload = PayloadGenerator.generateAccountPayload();

        // Act - Attempting to create the account
        Response attemptCreateAccount = accountsClient.createAccount(payload);

        // Assert - The result of the action is validated
        attemptCreateAccount
                .then()
                .statusCode(201)
                .body("nome", is(payload.getNome()))
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Should not create an account with a duplicate name")
    void shouldNotCreateAccountWithDuplicateName() {
        // Arrange - A specific payload and account are created to precondition
        AccountPayload payload = PayloadGenerator.generateAccountPayload();
        TestDataSetup.createValidAccountAndGetId(payload);

        // Act - Attempting to create the same account again
        Response secondAttemptResponse = accountsClient.createAccount(payload);

        // Assert - The result of the action is validated
        secondAttemptResponse.then()
                .statusCode(400)
                .body("error", is("Já existe uma conta com esse nome!"));
    }

    @Test
    @DisplayName("Should update account successfully")
    void shouldUpdateAccountSuccessfully() {
        // Arrange - Create an account and obtain your ID
        AccountPayload originalPayload = PayloadGenerator.generateAccountPayload();
        Integer accountId = TestDataSetup.createValidAccountAndGetId(originalPayload);

        // Action - New data is prepared and the update action is executed
        AccountPayload updatePayload = PayloadGenerator.generateAccountEditPayload();
        Response updateAccount = accountsClient.updateAccount(accountId, updatePayload);

        // Assert - The response from the UPDATE action is validated.
        updateAccount
                .then()
                .statusCode(200)
                .body("nome", is(updatePayload.getNome()));
    }

}