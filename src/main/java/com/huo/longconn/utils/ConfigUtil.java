package com.huo.longconn.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.tree.DefaultExpressionEngine;
import org.apache.commons.configuration2.tree.DefaultExpressionEngineSymbols;
import org.apache.commons.configuration2.tree.xpath.XPathExpressionEngine;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ConfigUtil {

    private static XMLConfiguration config = null;
    private static final String CONFIG_XML_PATH = "/config.xml";

    static {
        try {
            Configurations configs = new Configurations();
            config = configs.xml(ConfigUtil.class.getResource(CONFIG_XML_PATH));
            XPathExpressionEngine xpathEngine = new XPathExpressionEngine();
            config.setExpressionEngine(xpathEngine);
        } catch (ConfigurationException e) {
            log.error("初始化ConfigUtil异常 {}", e);
        }
    }

    /**
     * 获取配置的字符串值
     *
     * @param configXPath 配置项路径
     * @return 配置项值
     */
    public static String getString(String configXPath) {
        return config.getString(configXPath, null);
    }

    /**
     * 获取配置的字符串值
     *
     * @param configXPath  配置项路径
     * @param defaultValue 配置项的默认值
     * @return 配置项值
     */
    public static String getString(String configXPath, String defaultValue) {
        return config.getString(configXPath, defaultValue);
    }

    /**
     * 获取配置的double数值
     *
     * @param configXPath  配置项路径
     * @param defaultValue 配置项的默认值
     * @return 配置项值
     */
    public static double getDouble(String configXPath, double defaultValue) {
        return config.getDouble(configXPath, defaultValue);
    }

    /**
     * 获取配置的浮点数值
     *
     * @param configXPath  配置项路径
     * @param defaultValue 配置项的默认值
     * @return 配置项值
     */
    public static float getFloat(String configXPath, float defaultValue) {
        return config.getFloat(configXPath, defaultValue);
    }

    /**
     * 获取配置的整型值
     *
     * @param configXPath  配置项路径
     * @param defaultValue 配置项的默认值
     * @return 配置项值
     */
    public static int getInt(String configXPath, int defaultValue) {
        return config.getInt(configXPath, defaultValue);
    }

    /**
     * 获取配置的长整型值
     *
     * @param configXPath  配置项路径
     * @param defaultValue 配置项的默认值
     * @return 配置项值
     */
    public static long getLong(String configXPath, long defaultValue) {
        return config.getLong(configXPath, defaultValue);
    }

    /**
     * 获取配置的boolean值
     *
     * @param configXPath  配置项路径
     * @param defaultValue 配置项的默认值
     * @return 配置项值
     */
    public static boolean getBoolean(String configXPath, boolean defaultValue) {
        return config.getBoolean(configXPath, defaultValue);
    }

    /**
     * 获取配置的List值
     *
     * @param configXPath 配置项路径
     * @return 配置项值
     */
    public static List<String> getList(String configXPath) {
        List<String> values = new ArrayList<String>();
        for (Object o : config.getList(configXPath)) {
            values.add(o.toString());
        }
        return values;
    }

    /**
     * 设置String配置项
     *
     * @param configXPath 配置项路径
     * @param value       配置项值
     */
    public static void setString(String configXPath, String value) {
        config.setProperty(configXPath, value);
    }

    /**
     * 设置int配置项
     *
     * @param configXPath 配置项路径
     * @param value       配置项值
     */
    public static void setInt(String configXPath, int value) {
        config.setProperty(configXPath, value);
    }

    /**
     * 设置Float配置项
     *
     * @param configXPath 配置项路径
     * @param value       配置项值
     */
    public static void setFloat(String configXPath, float value) {
        config.setProperty(configXPath, value);
    }

    /**
     * 设置Long配置项
     *
     * @param configXPath 配置项路径
     * @param value       配置项值
     */
    public static void setLong(String configXPath, long value) {
        config.setProperty(configXPath, value);
    }

    /**
     * 设置Boolean配置项
     *
     * @param configXPath 配置项路径
     * @param value       配置项值
     */
    public static void setBoolean(String configXPath, boolean value) {
        config.setProperty(configXPath, value);
    }

    /**
     * 删除指定配置项
     *
     * @param configXPath 配置项路径
     */
    public static void remove(String configXPath) {
        config.clearProperty(configXPath);
    }

    /**
     * 追加配置项
     *
     * @param configXPath
     * @param value
     */
    public static void add(String configXPath, Object value) {
        config.addProperty(configXPath, value);
    }
}