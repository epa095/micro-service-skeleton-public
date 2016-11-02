package com.vimond.service.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vimond.resources.datastructures.Asset;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Thelo on 10/10/16.
 */


@Produces(MediaType.APPLICATION_JSON)
@Path("transfer/asset")
public class AssetClient extends BaseClient {


    private static final Logger LOG = LoggerFactory.getLogger(AssetClient.class);

    private CategoriesClient catClient;

    private ObjectMapper mapper;


    public AssetClient(Client client, String url, int port, CategoriesClient catClient) {
        super(client, url, port);
        this.catClient = catClient;
        this.mapper = new ObjectMapper();
    }

    @GET
    public List<Asset> getAssets(@QueryParam("categoryId") String categoryId) {

        if (StringUtils.isBlank(categoryId)) {

            return getAllAsset();
        }

        return getAssetsByCategory(categoryId);
    }

    @GET
    @Path("/{assetId}")
    public String getAsset(@PathParam("assetId") String assetId) {


        String request = this.endpoint + "api/web/asset/" + assetId;
        Response response = this.get(request);

        return response.readEntity(String.class);
    }

    public String prepareCall(int pageSize, int pageStart) throws URISyntaxException {

        URI request = new URIBuilder()
                .setScheme("https")
                .setHost(this.url)
                .setPort(this.port)
                .setPath("/api/web/search/categories/2320/assets")
                .setParameter("query", "documentType:asset")
                .setParameter("start", String.valueOf(pageStart))
                .build();

        return request.toString();
    }

    public List<Asset> getAllAsset() {

        List<Asset> assets = new LinkedList<Asset>();
        boolean keepFetching = true;

        int size = 25;
        int start = 0;

        do {
            try {
                String request = prepareCall(size, start);

                Response response = this.get(request);
                int status = response.getStatus();
                if (status != Response.Status.OK.getStatusCode()){
                    LOG.error("Request to Solr micro services failed [Response.code]" + status);
                    keepFetching = false;
                    continue;
                }

                JsonNode json = response.readEntity(JsonNode.class);


                if (!json.has("assets") || !json.get("assets").has("asset")) {
                    keepFetching = false;
                    continue;
                }

                JsonNode assetsJson = json.get("assets").get("asset");
                List<Asset> assetsFetched =
                        Arrays.asList(mapper.readValue(assetsJson.traverse(), Asset[].class));
                assets.addAll(assetsFetched);


            } catch (URISyntaxException e1){
                LOG.error("Error making request : " + e1.getMessage());
            } catch (IOException e2){
                LOG.error("Error while parsing json file : " + e2.getMessage());
            }

            start++;
        } while (keepFetching);

        LOG.debug("Number of assets " + assets.size());

        return assets;
    }

    private List<Asset> getAssetsByCategory(String categoryId){

        String request = this.endpoint + "api/web/category/" + categoryId + "/assets";
        List<Asset> assets = new LinkedList<>();

        Response response = this.get(request);
        int status = response.getStatus();

        if (status != Response.Status.OK.getStatusCode()){
            LOG.error("Request to Solr micro services failed [Response.code]" + status);
            return assets;
        }

        JsonNode jsonResponse = response.readEntity(JsonNode.class);

        if (!jsonResponse.has("assets") || !jsonResponse.get("assets").has("asset")) {
            return assets;
        }

        try {
            JsonNode assetsJson = jsonResponse.get("assets").get("asset");
            assets = Arrays.asList(this.mapper.readValue(assetsJson.traverse(), Asset[].class));
        } catch (IOException e){
            LOG.error("Error while parsing the response : " + e.getMessage());
        }

        return assets;
    }
}