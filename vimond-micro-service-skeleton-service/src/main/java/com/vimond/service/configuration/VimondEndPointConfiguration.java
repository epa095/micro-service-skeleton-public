package com.vimond.service.configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.validation.constraints.NotNull;

/**
 * Created by Thelo on 12/10/16.
 */

public class VimondEndPointConfiguration implements Cloneable{

    @NotNull
    @JsonProperty
    private String endpoint;

    @JsonProperty(defaultValue = "443")
    private int port;

    public String getEndpoint() {
        return endpoint;
    }

    public int getPort() {
        return port;
    }
}
