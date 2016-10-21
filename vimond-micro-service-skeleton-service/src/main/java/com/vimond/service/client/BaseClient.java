package com.vimond.service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Thelo on 10/10/16.
 */
public abstract class BaseClient {


    private static final Logger LOG = LoggerFactory.getLogger(BaseClient.class);


    protected final Client client;

    protected final String endpoint;

    protected final String url;

    protected final int port;

    public BaseClient(Client httpClient, String url, int port){
        this.endpoint = "https://" + url + ":" + String.valueOf(port) + "/";
        this.client = httpClient;
        this.port = port;
        this.url = url;
    }

    protected Response get(String request){
        WebTarget webTarget = this.client.target(request);
        LOG.debug("web target url :" + request );
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        return invocationBuilder.get();
    }
}
