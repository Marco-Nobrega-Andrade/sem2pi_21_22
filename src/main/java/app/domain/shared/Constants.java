package app.domain.shared;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Constants {
    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String PARAMS_FILENAME = "config.properties";
    public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";
    public static final String PARAMS_RECOVERY_PERIOD = "Recovery.Period";
    public static final String PARAMS_SORT_METHOD_DESIGNATION = "SortingMethod.Designation";
    public static final String PARAMS_REPORT_TIME = "Report.Timer";
    public static final String PARAMS_REPORT_METHOD_DESIGNATION = "Type.Report";

    public static final String ROLE_NURSE = "NURSE";
    public static final String ROLE_RECEPCIONIST="RECEPCIONIST";
    public static final String ROLE_COORDINATOR="COORDINATOR";
    public static final String ROLE_SNSUSER="SNS_USER";

    public static final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMBERS = "1234567890";
    public static final String ALPHANUMERIC_CHARS = UPPER_CASE_LETTERS + LOWER_CASE_LETTERS + NUMBERS;
    public static final int PWD_LENGTH = 7;

}
