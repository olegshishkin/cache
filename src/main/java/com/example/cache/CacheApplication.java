package com.example.cache;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
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
                .setInstanceName("hazel-instance")
                .addMapConfig(getMapConfig());
    }

    private static MapConfig getMapConfig() {
        return new MapConfig()
                .setName("persons")
                .setEvictionPolicy(EvictionPolicy.LRU)
                .setTimeToLiveSeconds(20);
    }
}
