package io.giovannymassuia.cleanarch.core.domain.entity;

import java.util.Arrays;
import java.util.List;

public class Cpf {

    private final int FACTOR_DIGIT_1 = 10;
    private final int FACTOR_DIGIT_2 = 11;
    private final int MAX_DIGITS_1 = 9;
    private final int MAX_DIGITS_2 = 10;

    private final String value;

    public Cpf(String value) {
        if (!this.validate(value)) throw new IllegalStateException("Invalid CPF");
        this.value = value;
    }

    private boolean validate(String cpf) {
        cpf = this.extractDigits(cpf);
        if (this.isInvalidLength(cpf)) return false;
        if (this.isBlocked(cpf)) return false;
        int digit1 = this.calculateDigit(cpf, this.FACTOR_DIGIT_1, this.MAX_DIGITS_1);
        int digit2 = this.calculateDigit(cpf, this.FACTOR_DIGIT_2, this.MAX_DIGITS_2);
        String calculatedCheckDigit = String.valueOf(digit1).concat(String.valueOf(digit2));
        return this.getCheckDigit(cpf).equals(calculatedCheckDigit);
    }

    private String extractDigits(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    private boolean isInvalidLength(String cpf) {
        return cpf.length() != 11;
    }

    private boolean isBlocked(String cpf) {
        String digit1 = String.valueOf(cpf.charAt(0));
        return Arrays.stream(cpf.split("")).allMatch(s -> s.equals(digit1));
    }

    private int calculateDigit(String cpf, int factor, int max) {
        int total = 0;

        for (int digit : this.toDigitArray(cpf).stream().limit(max).toList()) {
            total += digit * factor--;
        }

        return (total % 11 < 2) ? 0 : (11 - total % 11);
    }

    private List<Integer> toDigitArray(String cpf) {
        return Arrays.stream(cpf.split("")).map(digit -> Integer.parseInt(digit)).toList();
    }

    private String getCheckDigit(String cpf) {
        return cpf.substring(9);
    }

    public String getValue() {
        return value;
    }
}
