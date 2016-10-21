package com.vimond.service.client;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vimond.resources.datastructures.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Thelo on 10/10/16.
 */

@Produces(MediaType.APPLICATION_JSON)
@Path("transfer/category")
public class CategoriesClient extends BaseClient{

    private static final Logger LOG = LoggerFactory.getLogger(CategoriesClient.class);

    private ObjectMapper mapper;

    public CategoriesClient(Client client, String url, int port){
        super(client, url, port);
        this.mapper = new ObjectMapper();
    }

    @GET
    public List<Category> getCategories(){

        String request = this.endpoint + "api/web/category/root/categories";
        Response response = this.get(request);

        JsonNode json = response.readEntity(JsonNode.class);
        List<Category> categories = null;

        LOG.debug("Json : " + json.toString());

        if (json.has("categories")){

            JsonNode catMetadata = json.get("categories");
            if (catMetadata.has("category")){
                json = catMetadata.get("category");
                try {
                    categories =
                            Arrays.asList(mapper.readValue(json.traverse(), Category[].class));
                    LOG.debug("Display categories " + categories);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return categories;
    }

    @GET
    @Path("/{categoryId}")
    public Category getCategory(@PathParam("categoryId") String categoryId){

        String request = this.endpoint + "api/web/category/" + categoryId;
        Response response = this.get(request);

        JsonNode json = response.readEntity(JsonNode.class);
        Category category = null;
        LOG.debug("Json : " + json.toString());
        if (json.has("category")){
                json = json.get("category");
                try {
                    category = mapper.readValue(json.traverse(), Category.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return category;
    }
}
