package com.muzixiao2.bakabooru.hub.client;

import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SourceClientFactory {
    private final Encoder encoder;
    private final Decoder decoder;

    public SourceClient createClient(String baseUrl) {
        return Feign.builder()
                .encoder(encoder)
                .decoder(decoder)
                .logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.BASIC)
                .target(SourceClient.class, baseUrl);
    }
}
