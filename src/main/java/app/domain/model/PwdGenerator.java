package app.domain.model;

import app.domain.shared.Constants;

import java.util.ArrayList;

public class PwdGenerator {
    
    public static String generatePassword() {
        Character[] pwd = new Character[Constants.PWD_LENGTH];
        ArrayList<Character> pwdChars= new ArrayList<>();
        String finalPwd = "";

        // Selecting 3 capital letters
        for (int i = 0; i < 3; i++) {
            pwdChars.add(Constants.UPPER_CASE_LETTERS.charAt((int) (Math.random()*Constants.UPPER_CASE_LETTERS.length())));
        }
        // Selecting 2 digits
        for (int i = 0; i < 2; i++) {
            pwdChars.add(Constants.NUMBERS.charAt((int) (Math.random()*Constants.NUMBERS.length())));
        }
        // Selecting remaining chars
        for (int i = 0; i < 2; i++) {
            pwdChars.add(Constants.ALPHANUMERIC_CHARS.charAt((int) (Math.random()*Constants.ALPHANUMERIC_CHARS.length())));
        }

        for (int i = 0; i < pwd.length; i++) {
            int charIndx = (int)(Math.random()*pwdChars.size());
            pwd[i]= pwdChars.get(charIndx);
            pwdChars.remove(charIndx);
        }
        for (int i = 0; i < pwd.length; i++) {
            finalPwd += pwd[i];
        }

        return finalPwd;
    }
}
