package com.huo.longconn;

import com.huo.longconn.utils.ConfigUtil;
import lombok.extern.log4j.Log4j2;

/**
 * Netty主类
 */
@Log4j2
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(ConfigUtil.getString("test"));
    }
}
