package com.vimond.service.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Thelo on 10/10/16.
 */
public class VimondMicroServiceConfiguration extends Configuration{

    private static final Logger LOG = LoggerFactory.getLogger(VimondMicroServiceConfiguration.class);

    @Valid
    @NotNull
    private HttpClientConfiguration httpClient = new HttpClientConfiguration();

    @JsonProperty("vimond-api")
    private VimondEndPointConfiguration endPointConfiguration = new VimondEndPointConfiguration();

    @JsonProperty
    public HttpClientConfiguration getHttpClientConfiguration() {
        return httpClient;
    }

    @JsonProperty
    public VimondEndPointConfiguration getVimondEndPointConfiguration(){
        return this.endPointConfiguration;
    }
}
