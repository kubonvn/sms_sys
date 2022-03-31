package com.examonline.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.MessageSource;

import java.util.Locale;

@Component
public class DicHelper {

    private final MessageSource messageSource;

    @Autowired
    public DicHelper(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String msg, String language){
        try{
            Locale locale = new Locale(language);
            return messageSource.getMessage(msg,null,locale);
        }catch (Exception e){
            return "Can not get message";
        }
    }
}
