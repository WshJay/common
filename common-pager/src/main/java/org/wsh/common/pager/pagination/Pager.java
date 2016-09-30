package org.wsh.common.pager.pagination;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang.StringUtils;
import org.wsh.common.pager.base.Query;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;


/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  分页组件
 * since Date： 2016/9/29 14:50
 */
public class Pager {
	// 当前页之前可以显示的最多链接数，大于此条链接将被隐藏
	private int previousPageCount;

	// 当前页之后可以显示的最多链接数，大于此条链接将被隐藏
	private int afterPageCount;

	// 当前页码
	private int currentPageNo;

	// 总页数
	private int totalPage;

	public Pager() {
		previousPageCount = 5;
		afterPageCount = 5;
	}

	/**
	 * 绘制/渲染分页
	 * 
	 * @return
	 */
	public String render(StringBuffer pageUrl, Query query, Pagination pagination) {

		StringBuffer queryStringBuffer = new StringBuffer();

		// 编译URL中的QueryString
		if(query != null){
			buildQueryString(query, queryStringBuffer);
		}
		
		// 计算当前页
		currentPageNo = 1; // 当前页
		totalPage = pagination.getTotalPage(); // 总页数

		if (pagination.getPP() > 0) {
			currentPageNo = pagination.getPP();
		}

		// 分页的HTML代码
		//StringBuffer output = new StringBuffer();
		//output.append("<div ").append("class=").append("\"page\"")
		//		.append(">");
		StringBuffer output = new StringBuffer();
		output.append("").append("").append("")
				.append("");

		// 上一页
		if (currentPageNo == 1) {
			output.append("<span class=\"disabled\">上一页</span>");
		} else {
			output.append("<a href=\"").append(pageUrl).append("?p=")
					.append(currentPageNo - 1).append(queryStringBuffer)
					.append("\" class=\"page-prev\" title=\"上一页\">上一页</a>");
		}

		// 数字前部分
		if (currentPageNo < previousPageCount) {
			for (int i = 1; i < currentPageNo; i++) {
				output.append("<a href=\"").append(pageUrl).append("?p=").append(i)
						.append(queryStringBuffer).append("\">").append(i)
						.append("</a>");
			}
		} else {
			output.append("<a href=\"").append(pageUrl).append("?p=1")
					.append(queryStringBuffer).append("\">1</a>");
			output.append("<a href=\"").append(pageUrl).append("?p=2")
					.append(queryStringBuffer).append("\">2</a>");
			output.append("...");

			for (int i = currentPageNo - 2; i < currentPageNo; i++) {
				output.append("<a href=\"").append(pageUrl).append("?p=").append(i)
						.append(queryStringBuffer).append("\">").append(i)
						.append("</a>");
			}
		}

		// 当前
		output.append("<span class=\"current\">").append(currentPageNo)
				.append("</span>");
		// 数字后部分
		for (int i = currentPageNo + 1; i < currentPageNo + afterPageCount
				&& i <= totalPage; i++) {
			output.append("<a href=\"").append(pageUrl).append("?p=").append(i)
					.append(queryStringBuffer).append("\">").append(i)
					.append("</a>");

		}
		if (currentPageNo + afterPageCount < totalPage) {
			output.append("...");
		}
		// 下一页
		if (currentPageNo == totalPage) {
			output.append("<span class=\"disabled\">下一页</span>");
		}else{
			output.append("<a href=\"").append(pageUrl).append("?p=")
			.append(currentPageNo + 1).append(queryStringBuffer)
			.append("\" class=\"page-next\" title=\"下一页\">下一页</a>");
			
		}
		// 跳到第x页
		output.append("<span class=\"none\">")
			  .append(" 共").append(totalPage).append("页 ")
			  .append(" 到第<input id=\"_jumpto\" type=\"text\" value=\"").append(currentPageNo).append("\" size=\"3\" name=\"_jumpto\"/>页<button type=\"button\" onclick=\"javascript:window.location='").append(pageUrl).append("?p='+document.getElementById('_jumpto').value+'").append(queryStringBuffer).append("'\">确定</button>")
			  .append("</span>");
		// 结束
		//output.append("</div>");
		
		//output.append("<div style=\"clear:both\">");

		return output.toString();
	}

	/**
	 * 绘制/渲染分页
	 * @author by jlq
	 * @return 
	 */
	public String renderAddAction(String pageUrl,Query query,Pagination pagination) {

		StringBuffer queryStringBuffer = new StringBuffer();

		// 编译URL中的QueryString
		if(query != null){
			buildQueryString(query, queryStringBuffer);
		}
		
		// 计算当前页
		currentPageNo = 1; // 当前页
		totalPage = pagination.getTotalPage(); // 总页数

		if (pagination.getPP() > 0) {
			currentPageNo = pagination.getPP();
		}

		// 分页的HTML代码
		//StringBuffer output = new StringBuffer();
		//output.append("<div ").append("class=").append("\"page\"")
		//		.append(">");
		StringBuffer output = new StringBuffer();
		output.append("").append("").append("")
				.append("");
         pageUrl=pageUrl.substring(0, pageUrl.length()-1);

		// 上一页
		if (currentPageNo == 1) {
			output.append("<span class=\"disabled\">上一页</span>");
		} else {
			output.append("<a href=\"javascript:").append(pageUrl)
					.append(currentPageNo - 1+")")
					.append("\" class=\"page-prev\" title=\"上一页\">上一页</a>");
		}

		// 数字前部分
		if (currentPageNo < previousPageCount) {
			for (int i = 1; i < currentPageNo; i++) {
				output.append("<a href=\"javascript:").append(pageUrl).append(i+")")
						.append("\">").append(i)
						.append("</a>");
			}
		} else {
			output.append("<a href=\"javascript:").append(pageUrl).append(1+")")
					.append("\">1</a>");
			output.append("<a href=\"javascript:").append(pageUrl).append(2+")")
					.append("\">2</a>");
			output.append("...");

			for (int i = currentPageNo - 2; i < currentPageNo; i++) {
				output.append("<a href=\"javascript:").append(pageUrl).append(i+")")
						.append("\">").append(i)
						.append("</a>");
			}
		}

		// 当前
		output.append("<span class=\"current\">").append(currentPageNo)
				.append("</span>");
		// 数字后部分
		for (int i = currentPageNo + 1; i < currentPageNo + afterPageCount
				&& i <= totalPage; i++) {
			output.append("<a href=\"javascript:").append(pageUrl).append(i+")")
					.append("\">").append(i)
					.append("</a>");

		}
		if (currentPageNo + afterPageCount < totalPage) {
			output.append("...");
		}
		// 下一页
		if (currentPageNo == totalPage) {
			output.append("<span class=\"disabled\">下一页</span>");
		}else{
			output.append("<a href=\"javascript:").append(pageUrl)
			.append(currentPageNo + 1+")")
			.append("\" class=\"page-next\" title=\"下一页\">下一页</a>");
			
		}
		
		
		// 跳到第x页
		output.append("<span class=\"none\">")
			  .append(" 共").append(totalPage).append("页 ")
			  .append(" 到第<input id=\"_jumpto\" type=\"text\" value=\"").append(currentPageNo).append("\" size=\"3\" name=\"_jumpto\"/>页<button type=\"button\" onclick=\"javascript:").append(pageUrl).append("document.getElementById('_jumpto').value)").append("\">确定</button>")
			  .append("</span>");
		// 结束
		//output.append("</div>");
		
		//output.append("<div style=\"clear:both\">");

		return output.toString();
	}
	/**
	 * 构造url中的queryString
	 * 
	 * @param query
	 * @param buffer
	 */
	private void buildQueryString(Query query, StringBuffer buffer) {
		BeanMap beanMap = new BeanMap(query);

		Iterator iter = beanMap.keyIterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			Object value = beanMap.get(key);

			// 去除key = class 的属性，去除value = null的属性或为""的字符串
			if (!StringUtils.equalsIgnoreCase(key, "class") && value != null && !StringUtils.isBlank(value.toString())) {

				String encodeValue = null;

				try {
					encodeValue = URLEncoder.encode(value.toString(), "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				buffer.append("&").append(key).append("=")
						.append(encodeValue);

			}
		}
	}
}
