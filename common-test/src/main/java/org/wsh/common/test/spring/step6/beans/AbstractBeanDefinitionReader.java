package org.wsh.common.test.spring.step6.beans;

import org.wsh.common.test.spring.step6.beans.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  从配置中读取BeanDefinitionReader
 * since Date： 2016/10/31 14:17
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private Map<String,BeanDefinition> registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.registry = new HashMap<String, BeanDefinition>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
