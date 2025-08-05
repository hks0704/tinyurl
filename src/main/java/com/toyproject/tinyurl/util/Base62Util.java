package com.toyproject.tinyurl.util;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class Base62Util {
    private static final String BASE62_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final Integer DIVISOR = 62;

    public String encoding(Long num) {
        StringBuilder sb=new StringBuilder();
        if (num == 0) {
            return "0";
        }
        while (num > 0){
            int remain = (int)(num%DIVISOR);
            sb.append(BASE62_CHARACTERS.charAt(remain));
            num /= DIVISOR;
        }
        return sb.reverse().toString();
    }

}
