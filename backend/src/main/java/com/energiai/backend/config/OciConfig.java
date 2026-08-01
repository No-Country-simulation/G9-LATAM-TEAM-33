package com.energiai.backend.config;

import com.oracle.bmc.auth.InstancePrincipalsAuthenticationDetailsProvider;
import com.oracle.bmc.functions.FunctionsInvokeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Configuration;



@Configuration
@Profile("oci")
public class OciConfig {
    @Value("${oci.functions.endpoint}")
    private String endpoint;

    @Bean
    public FunctionsInvokeClient functionsClient() {
        var provider = InstancePrincipalsAuthenticationDetailsProvider.builder().build();

        var client = FunctionsInvokeClient.builder().build(provider);

        client.setEndpoint(endpoint);

        return client;
    }
}


