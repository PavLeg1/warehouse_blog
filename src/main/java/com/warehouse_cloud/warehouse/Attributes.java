package com.warehouse_cloud.warehouse;

import lombok.Data;

@Data
public class Attributes {
    
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        CAPACITY,   // Вместимость склада
        CLASS,      // Уровень доступности
        SPACE       // Размер помещения
    }
}
