package by.teachmeskills.hw_12052023.utils;

import by.teachmeskills.hw_12052023.model.AccountStatus;

public class ConverterUtils {
    public static AccountStatus toAccountStatus(String status) {
        return status.equals("active") ? AccountStatus.ACTIVE : AccountStatus.DELETED;
    }
}
