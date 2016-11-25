package org.wsh.common.orm.mysql.mybatis;

import org.wsh.common.orm.mysql.mybatis.base.BuildFactory;
import org.wsh.common.orm.mysql.mybatis.bean.TableWapper;
import org.wsh.common.orm.mysql.mybatis.build.*;
import org.wsh.common.orm.mysql.mybatis.enums.OutPathKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mybatisorm {

	private static final List<BuildFactory> BUILD_LIST = new ArrayList<BuildFactory>();

	private static final String PROJECT_PATH = "E:\\CommonWorks\\common";

	static {
		BUILD_LIST.add(new BuildDao());
		BUILD_LIST.add(new BuildXml());
		BUILD_LIST.add(new BuildBean());
		BUILD_LIST.add(new BuildService());
		BUILD_LIST.add(new BuildServiceImpl());
	}

	public static void main(String[] args) throws Exception {
		List<TableWapper> tables = getTables();
		for (TableWapper t : tables) {
			for (BuildFactory b : BUILD_LIST) {
				b.buildTable(t);
			}
		}
	}

	private static List<TableWapper> getTables() throws Exception {
		TablesBuilder builder = new TablesBuilder();
		Map<OutPathKey,String> outPathMap = new HashMap<OutPathKey,String>();
		builder.setJdbcClass("com.mysql.jdbc.Driver");//驱动
		builder.setUrl("jdbc:mysql://localhost:3306/common");//数据库链接
		builder.setName("root");//数据库用户名
		builder.setPwd("wsh0704");//数据库密码
		builder.setPojoPackage("org.wsh.common.model.message");//pojo包地址
		builder.setDaoPackage("org.wsh.common.dao.message");//dao包地址
		builder.setServicePackage("org.wsh.common.service.api.message");
		builder.setServiceImplPackage("org.wsh.common.service.impl.message");
		outPathMap.put(OutPathKey.DEFULT,"F:/TableModel/");
		outPathMap.put(OutPathKey.DO,PROJECT_PATH + "/common-model/src/main/java/org/wsh/common/model/message/");
		outPathMap.put(OutPathKey.DAO,PROJECT_PATH + "/common-dao/src/main/java/org/wsh/common/dao/message/");
		outPathMap.put(OutPathKey.XML,PROJECT_PATH + "/common-dao/src/main/resources/mappers/message/");
		outPathMap.put(OutPathKey.SERVICE,PROJECT_PATH + "/common-api/src/main/java/org/wsh/common/service/api/message/");
		outPathMap.put(OutPathKey.SERVICE_IMPL,PROJECT_PATH + "/common-service/src/main/java/org/wsh/common/service/impl/message/");
		builder.setOutPathMap(outPathMap);//生成文件地址
		builder.setTableName("user_basic");//数据库表名 %全部
		return builder.build();
	}

}
