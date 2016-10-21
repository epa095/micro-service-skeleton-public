package com.vimond.service.health;

import com.codahale.metrics.health.HealthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Thelo on 10/10/16.
 */
public class ServiceHealthCheck extends HealthCheck{


    private static final Logger LOG = LoggerFactory.getLogger(ServiceHealthCheck.class);

    public static final String NAME = "dummy";

    protected Result check() throws Exception {
        return Result.healthy();
    }
}
