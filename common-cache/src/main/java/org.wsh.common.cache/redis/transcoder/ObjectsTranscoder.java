package org.wsh.common.cache.redis.transcoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectsTranscoder {
	final private static Logger logger = LoggerFactory.getLogger(ObjectsTranscoder.class);

	public static byte[] serialize(List value) {
		if (value == null) {
			throw new NullPointerException("Can't serialize null");
		}
		byte[] rv = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		try {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			for (Object user : value) {
				os.writeObject(user);
			}
			os.writeObject(null);
			os.close();
			bos.close();
			rv = bos.toByteArray();
		} catch (IOException e) {
			throw new IllegalArgumentException("Non-serializable object", e);
		} finally {
			close(os);
			close(bos);
		}
		return rv;
	}

	public static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
				logger.info("Unable to close %s", closeable, e);
			}
		}
	}

	public static List deserialize(byte[] in) {
		List list = new ArrayList();
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		try {
			if (in != null) {
				bis = new ByteArrayInputStream(in);
				is = new ObjectInputStream(bis);
				while (true) {
					Object user = is.readObject();
					if (user == null) {
						break;
					} else {
						list.add(user);
					}
				}
				is.close();
				bis.close();
			}
		} catch (IOException e) {
			logger.warn("Caught IOException decoding %d bytes of data", in == null ? 0 : in.length, e);
		} catch (ClassNotFoundException e) {
			logger.warn("Caught CNFE decoding %d bytes of data", in == null ? 0 : in.length, e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}

				if (bis != null) {
					bis.close();
				}

			} catch (Exception e) {

			}
		}
		return list;
	}
}
