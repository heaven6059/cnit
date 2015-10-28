/*
 * @fileName：NullSerializer.java    2013-5-13 下午05:59:10
 *
 * Copyright (c) 2012 MAP Technologies, Inc. All rights reserved.
 * <P>Title：<P>
 * <P>Description：<P>
 * <P>Copyright: Copyright (c) 2012 <P>
 * <P>Company: MAP <P>
 *  
 * @version 1.0.0
 *
 */
package com.cnit.yoyo.spider.common.base.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class JsonNullSerializer extends JsonSerializer<Object>
{
    public void serialize(Object value, JsonGenerator jgen,
            SerializerProvider provider) throws IOException,
            JsonProcessingException
    {
        jgen.writeString("");
    }
}
