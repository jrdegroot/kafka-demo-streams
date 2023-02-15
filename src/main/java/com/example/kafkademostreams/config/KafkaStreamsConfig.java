package com.example.kafkademostreams.config;

import com.example.kafkademostreams.model.Location;
import com.example.kafkademostreams.model.User;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.KafkaStreamBrancher;

import static com.example.kafkademostreams.config.KafkaTopicsConfiguration.*;

@Configuration
@EnableKafkaStreams
public class KafkaStreamsConfig {

    @Bean
    public KStream<String, User> kStream(StreamsBuilder streamsBuilder) {

        KStream<String, User> stream = streamsBuilder.stream(KafkaTopicsConfiguration.DEMO_TOPIC);
        stream.print(Printed.toSysOut());

        KStream<String, User> branchedStream = new KafkaStreamBrancher<String, User>()
                .branch((key, value) -> value.getLocation().equals(Location.NL), kStream -> kStream.to(DEMO_TOPIC_NL))
                .branch((key, value) -> value.getLocation().equals(Location.DE), kStream -> kStream.to(DEMO_TOPIC_DE))
                .branch((key, value) -> value.getLocation().equals(Location.BE), kStream -> kStream.to(DEMO_TOPIC_BE))
                .defaultBranch(kStream -> kStream.to(DEMO_TOPIC_UNKNOWN))
                .onTopOf(streamsBuilder.stream(DEMO_TOPIC));

        branchedStream.print(Printed.toSysOut());

        return branchedStream;
    }
}
