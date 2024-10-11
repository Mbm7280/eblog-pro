package com.echo.common.utils;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Random;
import java.util.UUID;

public class GenegateIDUtil {

    public static String generateUserID () {
        String currentStr = String.valueOf(DateUtil.current());
        String prefix = "USER";
        Random random = new Random();
        String randomNumber = String.format("%06d", random.nextInt(999999) + 100000);
        String uuidStr = UUID.randomUUID().toString();
        return prefix + uuidStr + currentStr + randomNumber;
    }


    public static String generateRoleID () {
        String currentStr = String.valueOf(DateUtil.current());
        String prefix = "ROLE";
        Random random = new Random();
        String randomNumber = String.format("%06d", random.nextInt(999999) + 100000);
        String uuidStr = UUID.randomUUID().toString();
        return prefix + uuidStr + currentStr + randomNumber;
    }

    public static String generateResourceID () {
        String currentStr = String.valueOf(DateUtil.current());
        String prefix = "RESOURCE";
        Random random = new Random();
        String randomNumber = String.format("%06d", random.nextInt(999999) + 100000);
        String uuidStr = UUID.randomUUID().toString();
        return prefix + uuidStr + currentStr + randomNumber;
    }

    public static String generateRRID () {
        String currentStr = String.valueOf(DateUtil.current());
        String prefix = "ROLE&RESOURCE";
        Random random = new Random();
        String randomNumber = String.format("%06d", random.nextInt(999999) + 100000);
        String uuidStr = UUID.randomUUID().toString();
        return prefix + uuidStr + currentStr + randomNumber;
    }

    public static String generateURID () {
        String currentStr = String.valueOf(DateUtil.current());
        String prefix = "USER&ROLE";
        Random random = new Random();
        String randomNumber = String.format("%06d", random.nextInt(999999) + 100000);
        String uuidStr = UUID.randomUUID().toString();
        return prefix + uuidStr + currentStr + randomNumber;
    }

    public static String generateArticleID () {
        String currentStr = String.valueOf(DateUtil.current());
        String prefix = "ARTICLE";
        Random random = new Random();
        String randomNumber = String.format("%06d", random.nextInt(999999) + 100000);
        String uuidStr = UUID.randomUUID().toString();
        return prefix + uuidStr + currentStr + randomNumber;
    }

    public static String generateACID () {
        String currentStr = String.valueOf(DateUtil.current());
        String prefix = "ARTICLE&CATEGORY";
        Random random = new Random();
        String randomNumber = String.format("%06d", random.nextInt(999999) + 100000);
        String uuidStr = UUID.randomUUID().toString();
        return prefix + uuidStr + currentStr + randomNumber;
    }

    public static String generateFLID () {
        String currentStr = String.valueOf(DateUtil.current());
        String prefix = "FRIENDLINK";
        Random random = new Random();
        String randomNumber = String.format("%06d", random.nextInt(999999) + 100000);
        String uuidStr = UUID.randomUUID().toString();
        return prefix + uuidStr + currentStr + randomNumber;
    }

    public static String generateCommentID () {
        String currentStr = String.valueOf(DateUtil.current());
        String prefix = "COMMENT";
        Random random = new Random();
        String randomNumber = String.format("%06d", random.nextInt(999999) + 100000);
        String uuidStr = UUID.randomUUID().toString();
        return prefix + uuidStr + currentStr + randomNumber;
    }

    public static String generateAboutID () {
        String currentStr = String.valueOf(DateUtil.current());
        String prefix = "ABOUT";
        Random random = new Random();
        String randomNumber = String.format("%06d", random.nextInt(999999) + 100000);
        String uuidStr = UUID.randomUUID().toString();
        return prefix + uuidStr + currentStr + randomNumber;
    }

    public static void main(String[] args) {
        String roleID = generateAboutID();
        System.out.println(roleID);
    }

}
