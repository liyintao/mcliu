package com.lj.mcliu.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lj.mcliu.util.mail.MailSenderInfo;
import com.lj.mcliu.util.mail.SimpleMailSender;

public class Test {

    public static void main(String[] args) {
//        testHashMap();

//        EncryptUtil encryptUtil = new EncryptUtil();
//        String aesStr = encryptUtil.encrypt("123456");
//        System.out.println(aesStr);

//        boolean isEmail = "871702098@a.c".matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
//        System.out.println(isEmail);

//        StringBuffer stringBuffer = new StringBuffer();
//        if (!isSaveValue(stringBuffer)) {
//            System.out.println("stringBuffer.toString():\t" + stringBuffer.toString());
//        }

//        String string = String.valueOf("aaaaa");
//        System.out.println(string);

        // 这个类主要是设置邮件
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.qq.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUserName("3207475570@qq.com");
        mailInfo.setPassword("password");// 您的邮箱密码
        mailInfo.setFromAddress("3207475570@qq.com");
        mailInfo.setToAddress("liujiahenan@gmail.com");
        mailInfo.setSubject("设置邮箱标题 如http://www.guihua.org 中国桂花网");
        mailInfo.setContent("设置邮箱内容 如http://www.guihua.org 中国桂花网 是中国最大桂花网站==");
        // 这个类主要来发送邮件
        SimpleMailSender sms = new SimpleMailSender();
        //sms.attachfile("D:\\game.rar");
        sms.sendTextMail(mailInfo);// 发送文体格式
        sms.sendHtmlMail(mailInfo);// 发送html格式
    }

    private static boolean isSaveValue(StringBuffer stringBuffer) {
        stringBuffer.append("aaaaaaaaaaaaaaaaa");
        return false;
    }

    private static void testHashMap() {
        List<User> xmlUserList = initXmlUserData();
        List<User> dbUserList = initDBUserData();

        testSysoUserList(xmlUserList);
        System.out.println("----------------");
        testSysoUserList(dbUserList);
        System.out.println("------------------------------------------------------------");

//        Map<Integer, User> xmlUserMap = createUserMap(xmlUserList);
        Map<Integer, User> dbUserMap = createUserMap(dbUserList);

        List<User> finalUserList = new ArrayList<User>();
        for (User user : xmlUserList) {
            int key = user.getId();
            if (dbUserMap.keySet().contains(key)) {
                User dbUser = dbUserMap.get(key);
                user.setUserName(dbUser.getUserName());
                finalUserList.add(user);
            } else {
                finalUserList.add(user);
            }
        }

        testSysoUserList(finalUserList);
    }

    private static List<User> initXmlUserData() {
        List<User> xmlUserList = new ArrayList<User>();
        for (int i = 1; i <= 5; i++) {
            User user = new User();
            user.setId(i);
            user.setAge(20 + i);
            user.setUserName("xmlUserName" + i);
            xmlUserList.add(user);
        }
        return xmlUserList;
    }

    private static List<User> initDBUserData() {
        List<User> dbUserList = new ArrayList<User>();
        for (int i = 1; i <= 5; i++) {
            if (i == 2 || i == 3) {
                continue;
            }
            User user = new User();
            user.setId(i);
            user.setAge(20 + i);
            user.setUserName("dbUserName" + i);
            dbUserList.add(user);
        }
        return dbUserList;
    }

    private static void testSysoUserList(List<User> userList) {
        for (User user : userList) {
            System.out.println(user.getId());
            System.out.println(user.getUserName());
        }
    }

    private static Map<Integer, User> createUserMap(List<User> userList) {
        Map<Integer, User> userMap = new HashMap<Integer, User>();
        for (User user : userList) {
            userMap.put(user.getId(), user);
        }
        return userMap;
    }

}

class User {
    private int id;
    private String userName;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}