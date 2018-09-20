package com.huo.longconn;

import lombok.extern.log4j.Log4j2;

/**
 * Hello world!
 */
@Log4j2
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        for (; ; ) {

            log.debug("debug");
            log.info("info");
            log.error("error");
        }
    }
}
