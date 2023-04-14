package com.warehouse_cloud.warehouse.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.HashMap;
import com.warehouse_cloud.warehouse.Attributes;
import com.warehouse_cloud.warehouse.Attributes.Type;;


@Component
public class AttributesByIdConverter implements Converter<String, Attributes>{
    
    private Map<String, Attributes> attributesMap = new HashMap<>();

    public AttributesByIdConverter() {
        attributesMap.put("2KK", new Attributes("2KK", "2000 tons", Type.CAPACITY));
        attributesMap.put("1KK", new Attributes("1KK", "1000 tons", Type.CAPACITY));
        attributesMap.put("RICH", new Attributes("RICH", "Premium class", Type.CLASS));
        attributesMap.put("POOR", new Attributes("POOR", "Normal class", Type.CLASS));
        attributesMap.put("RSI3", new Attributes("RSI3", "Room size 300 m3", Type.SPACE));
        attributesMap.put("RSI5", new Attributes("RSI5", "Room size 500 m3", Type.SPACE));
    }

    @Override
    public Attributes convert(String id){
        return attributesMap.get(id);
    }

}
