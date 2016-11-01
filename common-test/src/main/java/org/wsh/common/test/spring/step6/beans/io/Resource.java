package org.wsh.common.test.spring.step6.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  Resource是spring内部定位资源的接口
 * since Date： 2016/10/31 14:16
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
