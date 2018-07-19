package com.jgc.jaegerdemo;

import com.uber.jaeger.samplers.ConstSampler;
import io.opentracing.Tracer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * https://my.oschina.net/u/2548090/blog/1821359
 */

@Configuration
public class TracingBeansConfig {

    @Bean
    public Tracer jaegerTracer() {
        com.uber.jaeger.Configuration.SenderConfiguration senderConfiguration = new com.uber.jaeger.Configuration.SenderConfiguration()
                .withAgentHost("localhost")
                .withAgentPort(6831);

        com.uber.jaeger.Configuration.ReporterConfiguration reporterConfiguration = new com.uber.jaeger.Configuration.ReporterConfiguration()
                .withSender(senderConfiguration)
                .withLogSpans(false)
                .withMaxQueueSize(1000)
                .withFlushInterval(100);

        com.uber.jaeger.Configuration.SamplerConfiguration samplerConfiguration = new com.uber.jaeger.Configuration.SamplerConfiguration()
                .withType(ConstSampler.TYPE)
                .withParam(1);

        com.uber.jaeger.Configuration configuration = new com.uber.jaeger.Configuration("jaeger-demo")
                .withReporter(reporterConfiguration)
                .withSampler(samplerConfiguration);

        return configuration.getTracer();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
