package org.acme.rest.json;

import io.quarkus.arc.Arc;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Path("/reproducer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class ReproducerResource {

    private final ReproducerTask reproducerTask;

    @Inject
    public ReproducerResource(ReproducerTask reproducerTask) {
        /*Hack to add ReproducerTask to Arc*/
        this.reproducerTask = reproducerTask;
    }

    @POST
    @Path("/import")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    @Transactional
    public Response reproduce(@MultipartForm ReproduceDto reproduceDto) {
        String myData = inputStreamToString(reproduceDto.inputStream);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        ReproducerTask subscriberImportTask = Arc.container().instance(ReproducerTask.class).get();

        reproducerTask.setData(myData);
        reproducerTask.setMyMapping(reproduceDto.myMapping);
        reproducerTask.setFlag(reproduceDto.flag);

        reproducerTask.setMyChar(reproduceDto.myChar);
        reproducerTask.setReproduceEnum(reproduceDto.reproduceEnum);
        executor.execute(subscriberImportTask);

        return Response.ok().build();
    }

    String inputStreamToString(InputStream inputStream) {
        try (
                final StringWriter writer = new StringWriter();
                final InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)
        ) {
            reader.transferTo(writer);
            return writer.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
