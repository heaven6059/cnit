package com.cnit.yoyo.spider.common.base.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;


public class DateSerializer extends JsonSerializer<Date> {

    public void serialize(Date date, JsonGenerator jgen, SerializerProvider provider) throws IOException,
            JsonProcessingException {
    	SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        jgen.writeString(sdf.format(date));
    }

}
