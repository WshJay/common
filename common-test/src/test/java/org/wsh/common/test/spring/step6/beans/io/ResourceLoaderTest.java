package org.wsh.common.test.spring.step6.beans.io;

import org.junit.Assert;
import org.junit.Test;
import org.wsh.common.test.spring.step6.beans.io.Resource;
import org.wsh.common.test.spring.step6.beans.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016/10/31 14:19
 */
public class ResourceLoaderTest {

	@Test
	public void test() throws IOException {
		ResourceLoader resourceLoader = new ResourceLoader();
        Resource resource = resourceLoader.getResource("spring/ioc/step6/tinyioc.xml");
        InputStream inputStream = resource.getInputStream();
        Assert.assertNotNull(inputStream);
    }
}
