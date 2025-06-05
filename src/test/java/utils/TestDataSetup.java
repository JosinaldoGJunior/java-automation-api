package utils;

import clients.AccountsClient;
import clients.TransactionsClient;
import models.AccountPayload;
import models.TransactionPayload;


public class TestDataSetup {

    private static final AccountsClient accountsClient = new AccountsClient();
    private static final TransactionsClient transactionsClient = new TransactionsClient();

    /**
     * Shortcut to create a valid account with a dynamically generated payload and return its ID.
     * Use this when the test DOES NOT need to know the specifics of the account data.
     * @return The ID (Integer) of the created account.
     */
    public static Integer createValidAccountAndGetId() {
        // Creates its own payload internally
        AccountPayload payload = PayloadGenerator.generateAccountPayload();
        return createValidAccountAndGetId(payload);
    }

    /**
     * Shortcut to create a valid account using a SPECIFIC payload and return its ID.
     * Use this when the test needs to control or reuse the payload data for its assertions.
     * @param payload The specific AccountPayload to be used for account creation.
     * @return The ID (Integer) of the created account.
     */
    public static Integer createValidAccountAndGetId(AccountPayload payload) {
        return accountsClient.createAccount(payload)
                .then()
                .statusCode(201) // Asserts that the setup was successful
                .extract().path("id");
    }

    // --- Transaction Setup Methods ---

    /**
     * Creates a valid INCOME transaction for a given account.
     * This is a common helper for tests that need a positive transaction as a precondition.
     * @param accountId The ID of the account this transaction belongs to.
     * @return The ID of the created income transaction.
     */
    public static Integer createValidIncomeAndGetId(Integer accountId) {
        // Uses the specific generator for an INCOME payload
        TransactionPayload payload = PayloadGenerator.generateNewIncomePayload(accountId);
        // Calls the base method to perform the API call and extraction
        return createValidTransactionAndGetId(payload);
    }

    /**
     * Creates a valid EXPENSE transaction for a given account.
     * This is a common helper for tests that need a negative transaction as a precondition.
     * @param accountId The ID of the account this transaction belongs to.
     * @return The ID of the created expense transaction.
     */
    public static Integer createValidExpenseAndGetId(Integer accountId) {
        // Uses the specific generator for an EXPENSE payload
        TransactionPayload payload = PayloadGenerator.generateNewExpensePayload(accountId);
        // Calls the base method to perform the API call and extraction
        return createValidTransactionAndGetId(payload);
    }

    /**
     * Base method to create a transaction with a specific payload and return its ID.
     * This is the core worker method used by other helpers. It's ideal for complex tests
     * where the payload is custom-built within the test itself.
     * @param payload The specific payload to use for creation. It must contain the accountId.
     * @return The ID of the created transaction.
     */
    public static Integer createValidTransactionAndGetId(TransactionPayload payload) {
        return transactionsClient.createTransaction(payload)
                .then()
                .statusCode(201)
                .extract().path("id");
    }

}