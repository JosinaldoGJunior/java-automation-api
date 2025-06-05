package utils;

import models.AccountPayload;
import models.TransactionPayload;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

public class PayloadGenerator {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String YESTERDAYS_DATE = LocalDate.now().minusDays(1).format(FORMATTER);
    private static final String FUTURE_DATE = LocalDate.now().plusDays(5).format(FORMATTER);
    private static final Random RANDOM = new Random();

    public static AccountPayload generateAccountPayload() {
        String randomName = "Account_" + randomString(5);
        return new AccountPayload(randomName);
    }

    public static AccountPayload generateAccountEditPayload() {
        String randomName = "E_Account_" + randomString(5);
        return new AccountPayload(randomName);
    }

    /**
     * Generates a payload for a new INCOME transaction with today's date.
     * Guarantees the type is "REC".
     * @param accountId The account ID for this transaction.
     * @return A new TransactionPayload for an income.
     */
    public static TransactionPayload generateNewIncomePayload(Integer accountId) {
        return generateBasePayload(accountId, "REC", YESTERDAYS_DATE, true);
    }

    /**
     * Generates a payload for a new EXPENSE transaction with today's date.
     * Guarantees the type is "DESP".
     * @param accountId The account ID for this transaction.
     * @return A new TransactionPayload for an expense.
     */
    public static TransactionPayload generateNewExpensePayload(Integer accountId) {
        return generateBasePayload(accountId, "DESP", YESTERDAYS_DATE, false);
    }

    /**
     * Generates a payload for an EXPENSE transaction with a future date.
     * Ideal for testing business rule validation.
     * @param accountId The account ID for this transaction.
     * @return A new TransactionPayload for a future-dated expense.
     */
    public static TransactionPayload generateFutureDateExpensePayload(Integer accountId) {
        return generateBasePayload(accountId, "DESP", FUTURE_DATE, false);
    }



    /**
     * Private helper to build the common structure of a transaction payload.
     * This avoids code duplication.
     */
    private static TransactionPayload generateBasePayload(Integer accountId, String type, String date, boolean status) {
        double randomValue = randomValor(100.0, 2000.0);

        return new TransactionPayload(
                accountId,
                "Transaction " + randomString(5),
                "Interested Party " + randomString(8),
                type,
                date,
                date,
                randomValue,
                status
        );
    }

    private static String randomString(int length) {
        return UUID.randomUUID().toString().replace("-", "").substring(0, length);
    }

    private static double randomValor(double min, double max) {
        double value = min + (max - min) * RANDOM.nextDouble();
        return Math.round(value * 100.0) / 100.0;
    }
}