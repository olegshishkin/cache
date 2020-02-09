package com.example.cache;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.config.MaxSizeConfig.MaxSizePolicy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching
@SpringBootApplication
public class CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }

    @Bean
    public Config hazelCastConfig() {
        return new Config()
                .setInstanceName("hazelcast-instance")
                .addMapConfig(new MapConfig()
                        .setName("persons")
                        .setEvictionPolicy(EvictionPolicy.LRU)
                        .setMaxSizeConfig(new MaxSizeConfig()
                                .setSize(1800)
                                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE))
                        .setTimeToLiveSeconds(20));
    }
}
