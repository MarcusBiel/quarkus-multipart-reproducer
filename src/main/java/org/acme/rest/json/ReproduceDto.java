package org.acme.rest.json;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.util.Map;

public class ReproduceDto {

    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream inputStream;

    @FormParam("myMapping")
    @PartType(MediaType.APPLICATION_JSON)
    //Alternative even: @PartType(MediaType.TEXT_PLAIN)
    public Map<Integer, String> myMapping;

    @FormParam("flag")
    @PartType(MediaType.TEXT_PLAIN)
    public boolean flag;

    @FormParam("myChar")
    @PartType(MediaType.TEXT_PLAIN)
    public char myChar;

    @FormParam("reproduceEnum")
    @PartType(MediaType.TEXT_PLAIN)
    public ReproduceEnum reproduceEnum;
}


