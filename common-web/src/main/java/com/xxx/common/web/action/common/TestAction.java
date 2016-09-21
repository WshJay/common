package com.xxx.common.web.action.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
public class TestAction {

	@RequestMapping("/test")
	public @ResponseBody JSONObject test(){
		String data = "[{'10104006':[{'apply_code':'002773','content':'康弘药业002773于6月17日在深交所发行,总量4560万股,发行价13.62元/股,申购股数500股整数倍,单一帐户上限1.35万股','diluted_pe_ratio':'22.979999542236328','issue_price':'13.6200','list_date':'','notice_date':'2015-06-17','notice_type':'新股网上发行','secu_abbr':'康弘药业','secu_code':'002773','secu_info':'康弘药业002773'},{'apply_code':'300485','content':'赛升药业300485于6月17日在深交所发行,总量3000万股,发行价38.46元/股,申购股数500股整数倍,单一帐户上限1.2万股','diluted_pe_ratio':'22.989999771118164','issue_price':'38.4600','list_date':'','notice_date':'2015-06-17','notice_type':'新股网上发行','secu_abbr':'赛升药业','secu_code':'300485','secu_info':'赛升药业300485'},{'apply_code':'002772','content':'众兴菌业002772于6月17日在深交所发行,总量3725万股,发行价13元/股,申购股数500股整数倍,单一帐户上限1.45万股','diluted_pe_ratio':'22.979999542236328','issue_price':'13.0000','list_date':'','notice_date':'2015-06-17','notice_type':'新股网上发行','secu_abbr':'众兴菌业','secu_code':'002772','secu_info':'众兴菌业002772'},{'apply_code':'002773','content':'发行4560万股,其中网下发行3192万股,发行价13.62元/股,网下申购缴款日为6月17日','diluted_pe_ratio':'22.979999542236328','issue_price':'13.6200','list_date':'','notice_date':'2015-06-17','notice_type':'新股网下发行','secu_abbr':'康弘药业','secu_code':'002773','secu_info':'康弘药业002773'},{'apply_code':'732117','content':'发行不超过6000万股,其中网下发行4200万股,网下申购缴款日为6月17日至6月18日','diluted_pe_ratio':'22.979999542236328','issue_price':'5.9300','list_date':'','notice_date':'2015-06-17','notice_type':'新股网下发行','secu_abbr':'万林申购','secu_code':'603117','secu_info':'万林申购732117'},{'apply_code':'780211','content':'发行不超过152500万股,其中网下发行106750万股,网下申购缴款日为6月17日至6月18日','diluted_pe_ratio':'22.989999771118164','issue_price':'19.7100','list_date':'','notice_date':'2015-06-17','notice_type':'新股网下发行','secu_abbr':'国君申购','secu_code':'601211','secu_info':'国君申购780211'},{'apply_code':'732116','content':'发行不超过5880万股,其中网下发行4116万股,网下申购缴款日为6月17日至6月18日','diluted_pe_ratio':'22.940000534057617','issue_price':'17.7000','list_date':'','notice_date':'2015-06-17','notice_type':'新股网下发行','secu_abbr':'蜻蜓申购','secu_code':'603116','secu_info':'蜻蜓申购732116'},{'apply_code':'732589','content':'发行不超过6000万股,其中网下发行4200万股,网下申购缴款日为6月17日至6月18日','diluted_pe_ratio':'22.979999542236328','issue_price':'16.0000','list_date':'','notice_date':'2015-06-17','notice_type':'新股网下发行','secu_abbr':'口子申购','secu_code':'603589','secu_info':'口子申购732589'},{'apply_code':'300485','content':'发行3000万股,其中网下发行1800万股,发行价38.46元/股,网下申购缴款日为6月17日 ','diluted_pe_ratio':'22.989999771118164','issue_price':'38.4600','list_date':'','notice_date':'2015-06-17','notice_type':'新股网下发行','secu_abbr':'赛升药业','secu_code':'300485','secu_info':'赛升药业300485'},{'apply_code':'002772','content':'发行3725万股,其中网下发行2235万股,发行价13元/股,网下申购缴款日为6月17日 ','diluted_pe_ratio':'22.979999542236328','issue_price':'13.0000','list_date':'','notice_date':'2015-06-17','notice_type':'新股网下发行','secu_abbr':'众兴菌业','secu_code':'002772','secu_info':'众兴菌业002772'}]}]";
		JSONObject obj = new JSONObject();
		obj.put("data", JSON.parse(data));
		return obj;
	}
}

