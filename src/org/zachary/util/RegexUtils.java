package org.zachary.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ZacharyChang.
 */
public class RegexUtils {
    public static String getFirstString(String dealStr, String regexStr) {
        if (dealStr == null || regexStr == null) {
            return null;
        }
        Pattern pattern = Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(dealStr);
        if (matcher.find()) {
            return matcher.group().trim();
        }
        return null;
    }

    public static List<String> getList(String dealStr, String regexStr) {
        List<String> list = new ArrayList<>();
        if (dealStr == null || regexStr == null) {
            return null;
        }
        Pattern pattern = Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(dealStr);
        while (matcher.find()) {
            list.add(matcher.group().trim());
        }
        return list;
    }

    public static void main(String[] args) {
        String dealStr = "afds23423aasfdsadasfa";
        String regexStr = "a(.*?)a";
        System.out.println(RegexUtils.getFirstString(dealStr, regexStr));
        System.out.println(RegexUtils.getList(dealStr, regexStr));
    }
}
