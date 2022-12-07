package com.example.telephonebillingapplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = {"test"})
public abstract class IntegrationTest {

    static {
        System.setProperty("log.json.disable", "true");
    }

    @LocalServerPort
    protected int serverPort;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired(required = false)
    protected CacheManager cacheManager;

    protected abstract void clearDb();

    @Before
    public void setup() {
        RestAssured.port = this.serverPort;
        RestAssured.config = RestAssured.config().objectMapperConfig(RestAssured.config().getObjectMapperConfig()
                .jackson2ObjectMapperFactory((cls, charset) -> objectMapper));
        RestAssured.requestSpecification = new RequestSpecBuilder() //
                .setContentType(MediaType.APPLICATION_JSON_VALUE) //
                .setAccept(MediaType.APPLICATION_JSON_VALUE) //
                .build();

        if (cacheManager != null) {
            cacheManager.getCacheNames().forEach(name -> cacheManager.getCache(name).clear());
        }
        clearDb();
    }
}

