package com.mridasoft.learning.infinspan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class InfinspanApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(InfinspanApplication.class);

	@Autowired
	private MusicService musicService;

	@Autowired
	private CacheManager cacheManager;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(InfinspanApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Spring Boot infinispan Caching Example Configuration");
		LOGGER.info("Using cache manager: " + cacheManager.getClass().getName());

		musicService.clearCache();

		play("trombone");
		play("guitar");
		play("trombone");
		play("bass");
		play("trombone");
	}

	private void play(String instrument) {
		LOGGER.info("Calling: " + MusicService.class.getSimpleName() + ".play(\"" + instrument + "\");");
		musicService.play(instrument);
	}
}
