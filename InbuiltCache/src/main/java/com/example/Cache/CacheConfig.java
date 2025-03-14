package com.example.Cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCaching
public class CacheConfig {

    private final CacheManager cacheManager;

    public CacheConfig(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("notes", "note");
    }

    @PostConstruct
    public void preloadCache() {
        List<Note> initialNotes = Arrays.asList(
                new Note(1L, "Welcome Note", "This is a preloaded note."),
                new Note(2L, "Spring Boot Caching", "Cache data is loaded at startup.")
        );

        // Store initial data in the cache
        for (Note note : initialNotes) {
            cacheManager.getCache("note").put(note.getId(), note);
        }

        System.out.println("✅ Preloaded cache with initial notes!");
    }
}

