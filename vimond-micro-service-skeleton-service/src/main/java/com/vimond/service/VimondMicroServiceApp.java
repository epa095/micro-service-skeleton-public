package com.vimond.service;

import com.vimond.service.algo.Knapsack;
import com.vimond.service.client.AssetClient;
import com.vimond.service.client.CategoriesClient;
import com.vimond.service.configuration.VimondEndPointConfiguration;
import com.vimond.service.engine.SearchEngine;
import com.vimond.service.health.ServiceHealthCheck;
import com.vimond.service.resources.sample.SampleResource;
import io.dropwizard.Application;
import com.vimond.service.configuration.VimondMicroServiceConfiguration;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import java.util.stream.Collectors;

/**
 * Created by Thelo on 11/10/16.
 */
public class VimondMicroServiceApp extends Application<VimondMicroServiceConfiguration> {

    private static final Logger LOG = LoggerFactory.getLogger(VimondMicroServiceApp.class);


    public static void main(String[] args) {
            try {
                new VimondMicroServiceApp().run(args);
            } catch (Exception e){
                LOG.error("Error while running micro service : " + e.getMessage());

            }
        }

        public String getName(){
            return "vimond-micro-service";
        }

        public void initialize(Bootstrap<VimondMicroServiceConfiguration> bootstrap){
        }

        public void run(VimondMicroServiceConfiguration configuration,
                        Environment environment){

            // Create health check
            final ServiceHealthCheck healthCheck =
                    new ServiceHealthCheck();
            environment.healthChecks().register("vimond-micro-service", healthCheck);

            // Create http client to query Vimond's micro services
            VimondEndPointConfiguration endpoint = configuration.getVimondEndPointConfiguration();

            final Client client = new JerseyClientBuilder(environment).build("Rest client");
            CategoriesClient catClient = new CategoriesClient(client, endpoint.getEndpoint(), endpoint.getPort());
            environment.jersey().register(catClient);
            AssetClient assetClient = new AssetClient(client, endpoint.getEndpoint(), endpoint.getPort(), catClient);
            environment.jersey().register(assetClient);

            environment.jersey().register(new Knapsack(assetClient.getAllAsset().stream().filter(asset -> asset.getDuration()>0).collect(Collectors.toList())));

            // Create resources
            SearchEngine engine = new SearchEngine(assetClient, catClient);
            final SampleResource resource = new SampleResource(engine);

            // Add resources
            environment.jersey().register(resource);

        }
}