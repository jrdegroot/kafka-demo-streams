package com.example.kafkademostreams.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfiguration {

    public final static String DEMO_TOPIC = "demo-topic";
    public final static String DEMO_TOPIC_NL = "demo-topic-nl";
    public final static String DEMO_TOPIC_DE = "demo-topic-de";
    public final static String DEMO_TOPIC_BE = "demo-topic-be";
    public final static String DEMO_TOPIC_UNKNOWN = "demo-topic-unknown";

    @Bean
    public NewTopic demoTopic() {
        return TopicBuilder.name(DEMO_TOPIC)
                .build();
    }

    @Bean
    public NewTopic demoTopicNL() {
        return TopicBuilder.name(DEMO_TOPIC_NL)
                .build();
    }

    @Bean
    public NewTopic demoTopicDE() {
        return TopicBuilder.name(DEMO_TOPIC_DE)
                .build();
    }

    @Bean
    public NewTopic demoTopicBE() {
        return TopicBuilder.name(DEMO_TOPIC_BE)
                .build();
    }

    @Bean
    public NewTopic demoTopicUNKNOWN() {
        return TopicBuilder.name(DEMO_TOPIC_UNKNOWN)
                .build();
    }



}
