package com.vimond.service.resources.sample;

import com.vimond.resources.datastructures.Asset;
import com.vimond.service.engine.SearchEngine;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thelo on 10/10/16.
 */

/**
 * Dead brain API sample to show one example of
 */

@Path("/sample")
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {

    private static final Logger LOG = LoggerFactory.getLogger(SampleResource.class);

    private SearchEngine searchEngine;

    public SampleResource(SearchEngine searchEngine){
        this.searchEngine = searchEngine;
    }

    @GET
    @Path("/search")
    public List<Asset> searchSample(@QueryParam("anOption") String anOption){


        // Add logic below.

        // Add param parsing here and security check.

        // Extract pattern to search
        String pattern = "";

        return searchEngine.searchByString(pattern);
    }
}
