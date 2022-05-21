package com.examonline.util;

public class Constant {
    public static class SCHEMA_ENTITY{
        public static final String EXAM_ONLINE_DB = "examonline";
    }
    public static class TIMES{
        public static final Long expiredTimePassword = 30*24*60*60*1000L;
    }
    public static class ROLE{
        public static final String ROLE_CODE_USER = "NORMAL";
        public static final String ROLE_CODE_ADMIN = "ADMIN";
    }
    public static class REGEX{
        public static final String USERNAME = "^[a-zA-z0-9_]{5,30}";
        public static final String PASSWORD = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        public static final String EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    }
    public static class RE_CAPTCHA{
        public static final String URL = "https://www.google.com/recaptcha/api/siteverify";
        public static final String SECRET = "6LdqpcEfAAAAADnDEy60wzPsqYxDRz6QX91qOGx9";
    }
}