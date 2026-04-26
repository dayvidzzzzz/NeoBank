package dy.system.minebanconovo.infra.utils;

public class CpfValidator {

    public static boolean isValid(String cpf) {
        if (cpf == null || cpf.isBlank())
            return false;

        String cleanedCpf = cpf.replaceAll("\\D", "");

        if (cleanedCpf.length() != 11)
            return false;

        if (cleanedCpf.matches("(\\d)\\1{10}"))
            return false;

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(cleanedCpf.charAt(i));
            sum += digit * (10 - i);
        }

        int firstVerifier = 11 - (sum % 11);
        if (firstVerifier >= 10) firstVerifier = 0;

        int firstDigitActual = Character.getNumericValue(cleanedCpf.charAt(9));
        if (firstVerifier != firstDigitActual)
            return false;

        sum = 0;
        for (int i = 0; i < 10; i++) {
            int digit = Character.getNumericValue(cleanedCpf.charAt(i));
            sum += digit * (11 - i);
        }

        int secondVerifier = 11 - (sum % 11);
        if (secondVerifier >= 10) secondVerifier = 0;

        int secondDigitActual = Character.getNumericValue(cleanedCpf.charAt(10));
        return secondVerifier == secondDigitActual;
    }
}
