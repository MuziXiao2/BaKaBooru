package com.muzixiao2.bakabooru.hub.client;

import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.form.spring.SpringFormEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.stereotype.Component;

@Component

public class SourceClientFactory {
    private final Decoder decoder;
    private final SpringFormEncoder encoder;

    public SourceClientFactory(ObjectFactory<HttpMessageConverters> messageConverters) {
        this.decoder = new SpringDecoder(messageConverters);
        this.encoder = new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

    public SourceClient createClient(String baseUrl) {
        return Feign.builder()
                .encoder(encoder)
                .decoder(decoder)
                .logger(new Slf4jLogger(SourceClient.class))
                .logLevel(Logger.Level.BASIC)
                .target(SourceClient.class, baseUrl);
    }
}
