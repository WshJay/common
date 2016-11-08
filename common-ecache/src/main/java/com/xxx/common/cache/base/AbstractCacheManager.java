package com.xxx.common.cache.base;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.shiro.util.LifecycleUtils;
import org.springframework.util.StringUtils;

public abstract class AbstractCacheManager implements CacheManager {

	/**
     * Retains all Cache objects maintained by this cache manager.
     */
    private final ConcurrentMap<String, Cache> caches;

    /**
     * Default no-arg constructor that instantiates an internal name-to-cache {@code ConcurrentMap}.
     */
    public AbstractCacheManager() {
        this.caches = new ConcurrentHashMap<String, Cache>();
    }

    /**
     * Returns the cache with the specified {@code name}.  If the cache instance does not yet exist, it will be lazily
     * created, retained for further access, and then returned.
     *
     * @param name the name of the cache to acquire.
     * @return the cache with the specified {@code name}.
     * @throws IllegalArgumentException if the {@code name} argument is {@code null} or does not contain text.
     * @throws CacheException           if there is a problem lazily creating a {@code Cache} instance.
     */
    public <K, V> Cache<K, V> getCache(String name) throws IllegalArgumentException, CacheException {
        if (!StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Cache name cannot be null or empty.");
        }

        Cache cache;

        cache = caches.get(name);
        if (cache == null) {
            cache = createCache(name);
            Cache existing = caches.putIfAbsent(name, cache);
            if (existing != null) {
                cache = existing;
            }
        }

        //noinspection unchecked
        return cache;
    }

    /**
     * Creates a new {@code Cache} instance associated with the specified {@code name}.
     *
     * @param name the name of the cache to create
     * @return a new {@code Cache} instance associated with the specified {@code name}.
     * @throws CacheException if the {@code Cache} instance cannot be created.
     */
    protected abstract Cache createCache(String name) throws CacheException;

    /**
     * Cleanup method that first {@link LifecycleUtils#destroy destroys} all of it's managed caches and then
     * {@link java.util.Map#clear clears} out the internally referenced cache map.
     *
     * @throws Exception if any of the managed caches can't destroy properly.
     */
    public void destroy() throws Exception {
        while (!caches.isEmpty()) {
            for (Cache cache : caches.values()) {
                LifecycleUtils.destroy(cache);
            }
            caches.clear();
        }
    }

    public String toString() {
        Collection<Cache> values = caches.values();
        StringBuilder sb = new StringBuilder(getClass().getSimpleName())
                .append(" with ")
                .append(caches.size())
                .append(" cache(s)): [");
        int i = 0;
        for (Cache cache : values) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(cache.toString());
            i++;
        }
        sb.append("]");
        return sb.toString();
    }
}

