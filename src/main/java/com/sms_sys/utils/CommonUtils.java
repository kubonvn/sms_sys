package com.sms_sys.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CommonUtils {

    public static boolean checkUsernameCharacters(String username) {
        return username.matches(Constant.REGEX.USERNAME);
    }

    public static boolean checkPasswordPolicy(String password) {
        List<String> passwordList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new FileInputStream("common/blacklist-password.txt"));
            while (scanner.hasNextLine()) {
                passwordList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
        }
        return password.matches(Constant.REGEX.PASSWORD) && !passwordList.contains(password);
    }

    public static String generateRandomPassword() {
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*()-_=+[{]}\\|;:,<.>/?";
        String randomPassword = "";
        while(true) {
            StringBuilder password = new StringBuilder();
            while (password.length() < 12) {		password.append(characters.charAt(random.nextInt(characters.length())));
            }
            if (checkPasswordPolicy(password.toString())) {
                randomPassword = password.toString();
                break;
            }
        }
        return randomPassword;
    }

    public static boolean checkPhoneNumber(String phoneNumber){
        return phoneNumber != null &&
                phoneNumber.matches("[0-9]+") &&
                phoneNumber.length() > 8 &&
                phoneNumber.length() < 14;
    }
    public static boolean checkEmail(String email) {
        if (email == null || email.isEmpty())
            return true;

        Pattern pat = Pattern.compile(Constant.REGEX.EMAIL);
        return pat.matcher(email).matches();
    }

}
