package com.example.muzkingproducts;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GlobalConfig {
    private static final String CONFIG_NAME = "product.properties";
    private static final Properties GLOBAL_CONFIG = new Properties();

    // Сделать начальную загрузку параметров из файла по умолчанию
    public static void initGlobalConfig() throws IOException {
        initGlobalConfig(null);
    }

    // Сделать загрузку данных из конфигурационного файла, имя которого передано в виде параметра
    // Если имя null или пустое - берем файл по умолчанию.
    public static void initGlobalConfig(String name) throws IOException {
        if (name != null && !name.trim().isEmpty()) {
            GLOBAL_CONFIG.load(new FileReader(name));
        } else {
            GLOBAL_CONFIG.load(new FileReader(CONFIG_NAME));
        }
    }

    // Получить значение параметра из глобальной конфигурации по имени
    public static String getProperty(String property) {
        return GLOBAL_CONFIG.getProperty(property);
    }
}
