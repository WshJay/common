package org.wsh.common.orm.mysql.mybatis.build;

import org.wsh.common.orm.mysql.mybatis.base.AbstractBuildFactory;
import org.wsh.common.orm.mysql.mybatis.bean.BigAndSmall;
import org.wsh.common.orm.mysql.mybatis.bean.Column;
import org.wsh.common.orm.mysql.mybatis.bean.Table;
import org.wsh.common.orm.mysql.mybatis.bean.TableWapper;
import org.wsh.common.orm.mysql.mybatis.enums.OutPathKey;
import org.wsh.common.orm.mysql.mybatis.util.Util;

import java.util.*;

import static org.wsh.common.orm.mysql.mybatis.util.Util.parseDate;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  构建模型
 * since Date： 2016/11/16 15:22
 */
public class BuildBean extends AbstractBuildFactory {

	@Override
	public OutPathKey getOutPath() {
		return OutPathKey.DO;
	}

	@Override
	public void buildTable(TableWapper tableWapper) {
		List<Column> column = new ArrayList<Column>();
		List<BigAndSmall> column2 = new ArrayList<BigAndSmall>();
		Map<String, Object> map = new HashMap<String, Object>();

		Table table = tableWapper.getTable();
		List<Column> columnList = table.getColumns();
		String doName = Util.getUpperHumpName(table.getName());
		String outPath = tableWapper.getOutPathMap().get(getOutPath());
		String packageName = tableWapper.getPojoPackage();
		for (Column co : columnList) {
			Column colu = new Column();
			BigAndSmall bigSmall = new BigAndSmall();
			String type = co.getType();
			if (type.equalsIgnoreCase("DATETIME") || type.equalsIgnoreCase("DATE") || type.equalsIgnoreCase("TIME")
					|| type.equalsIgnoreCase("TIMESTAMP")) {
				type = "Date";
			} else if (type.equalsIgnoreCase("VARCHAR") || type.equalsIgnoreCase("CHAR")
					|| type.equalsIgnoreCase("BLOB") || type.equalsIgnoreCase("TEXT")
					|| type.equalsIgnoreCase("LONGBLOB") || type.equalsIgnoreCase("LONGTEXT")) {
				type = "String";
			} else if (type.equalsIgnoreCase("ENUM")) {
				type = Util.getUpperHumpName(co.getName());
			} else if (type.equalsIgnoreCase("INT") || type.equalsIgnoreCase("TINYINT")) {
				type = "int";
			} else if (type.equalsIgnoreCase("BIGINT")) {
				type = "Long";
			} else if (type.equalsIgnoreCase("DOUBLE")) {
				type = "Double";
			} else if (type.equalsIgnoreCase("FLOAT")) {
				type = "Float";
			} else if (type.equalsIgnoreCase("INTEGER")) {
				type = "Integer";
			}
			if(Util.getHumpName(co.getName()).equalsIgnoreCase("id")||
					Util.getHumpName(co.getName()).equalsIgnoreCase("gmtCreated")||
					Util.getHumpName(co.getName()).equalsIgnoreCase("gmtModified")) continue;
			colu.setType(type);
			colu.setRemark(co.getRemark());
			colu.setName(Util.getHumpName(co.getName()));
			bigSmall.setBigName(Util.getUpperHumpName(co.getName()));
			bigSmall.setSmallName(Util.getHumpName(co.getName()));
			bigSmall.setType(type);

			column.add(colu);
			column2.add(bigSmall);
		}

		String head = "";
		doName = doName.replace("Tab", "");
		String headName = Util.getUpperHumpName(doName);
		head = doName;
		map.put("columList", column);
		map.put("columList2", column2);
		map.put("packageName", packageName);
		map.put("head", head);
		map.put("headName", headName);
		map.put("date", parseDate(new Date(),"yyyy-MM-dd HH:mm:ss"));

		Util.writeCode("bean", map, outPath + doName + "DO.java/");
	}
}
