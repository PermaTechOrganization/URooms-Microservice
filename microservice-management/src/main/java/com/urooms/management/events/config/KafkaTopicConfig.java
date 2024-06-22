package com.urooms.management.events.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic(){

        Map<String, String> configurations = new HashMap<>();
        //delete topic after x minute, compact (maintain only the last value for each key)
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        //message retention time
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");
        //segment size
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");
        //max message size
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1048576");


        return TopicBuilder.name("management-events")
                .partitions(2)
                .replicas(2)
                .configs(configurations)
                .build();
    }

}
