package com.spotify.orchestator.config;

import com.netflix.discovery.EurekaClient;
import com.spotify.orchestator.exceptions.MissingConfigurationException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import static java.util.Objects.isNull;
import static org.springframework.util.CollectionUtils.isEmpty;

@Component
public class WebClientConfig {

    @Autowired
    private EurekaClient eurekaClient;

    @Value("${service.info-finder-service.id}")
    private String infoFinderServiceId;
    @Value("${service.detect-service.id}")
    private String detectServiceId;

    public WebClientConfig(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @Bean("infoFinderServiceWebClientBuilder")
    public WebClient.Builder getInfoFinderServiceWebClientBuilder() {
        return getBuilder(infoFinderServiceId);
    }

    @Bean("detectServiceWebClientBuilder")
    public WebClient.Builder getDetectServiceWebClientBuilder() {
        return getBuilder(detectServiceId);
    }

    private WebClient.Builder getBuilder(String serviceId) {
        val application = eurekaClient.getApplication(serviceId);
        if (isNull(application) || isEmpty(application.getInstances())) {
            throw new MissingConfigurationException("Missing service " + serviceId);
        }
        val instanceInfo = application.getInstances().get(0);
        final String ipAddr = instanceInfo.getIPAddr();
        final int port = instanceInfo.getPort();
        String baseUrl = "http://" + ipAddr + ":" + port;
        return WebClient.builder()
                .baseUrl(baseUrl);
    }
}
