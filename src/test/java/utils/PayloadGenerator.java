package utils;

import models.AccountPayload;
import models.TransactionPayload;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

import static java.time.LocalDate.*;

public class PayloadGenerator {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final LocalDate NOW = now().minusDays(1);
    private static final String NOW_DATE = NOW.format(FORMATTER);
    private static final String FUTURE_DATE = NOW.plusDays(5).format(FORMATTER);
    private static final Random RANDOM = new Random();

    public static AccountPayload generateAccountPayload() {
        String randomName = "Account_" + randomString(5);
        return new AccountPayload(randomName);
    }

    public static AccountPayload generateAccountEditPayload() {
        String randomName = "E_Account_" + randomString(5);
        return new AccountPayload(randomName);
    }

    public static TransactionPayload generateTransactionPayload(Integer contaId) {
        return new TransactionPayload(
                contaId,
                "Descricao_" + randomString(5),
                "Envolvidos_" + randomString(5),
                randomTipo(),
                NOW_DATE,
                NOW_DATE,
                randomValor(500, 1000),
                RANDOM.nextBoolean()
        );
    }

    public static TransactionPayload generateFutureTransactionPayload(Integer contaId) {
        return new TransactionPayload(
                contaId,
                "Descricao_" + randomString(5),
                "Envolvidos_" + randomString(5),
                randomTipo(),
                FUTURE_DATE,
                FUTURE_DATE,
                randomValor(1, 1000),
                RANDOM.nextBoolean()
        );
    }

    private static String randomString(int length) {
        return UUID.randomUUID().toString().replace("-", "").substring(0, length);
    }

    private static String randomTipo() {
        return RANDOM.nextBoolean() ? "DESP" : "REC";
    }

    private static double randomValor(double min, double max) {
        double value = min + (max - min) * RANDOM.nextDouble();
        return Math.round(value * 100.0) / 100.0;
    }
}
