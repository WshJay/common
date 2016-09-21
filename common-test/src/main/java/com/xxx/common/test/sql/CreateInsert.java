package com.xxx.common.test.sql;

import com.xxx.common.util.file.FileUtil;
import sun.plugin.util.UIUtil;

import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

import static java.util.UUID.randomUUID;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-09-21 10:17
 */
public class CreateInsert {

    public static void main(String[] args) throws IOException {
        for (int j = 0; j < 10; j++) {
            StringBuffer content = new StringBuffer();
            for (int i = 0; i < 100000; i++) {
                String currentDate = "'2016-09-21 10:30:00'";
                String id = UUID.randomUUID().toString();
                content.append("INSERT INTO bill_withdraw (`id`, `billNumber`, `withdrawType`, `channelId`, `userId`, `accountId`, `bankCardNo`, `bankCode`, `bankName`, `businessNumber`, `businessBody`, `appId`, `businessDate`, `businessDesc`, `amount`, `sign`, `createDate`, `updateDate`, `description`, `version`, `isdeleted`) VALUES ('" + id + "', '" + UUID.randomUUID().toString() + "', 'UNTIME', 'LIANLIAN', '201512040929176188868d2365cd444ca833046f944178d97', '201512040929176188868d2365cd444ca833046f944178d97_shop', '6226660605677380', '03030000', '光大银行', '" + UUID.randomUUID().toString() + "', NULL, 'F75378884E4048C7AF8890DB9A38C541', '2016-09-21 10:20:12', '测试提现', '10.0000', NULL, " + currentDate + ", " + currentDate + ", NULL, '0', '0');");
                content.append("\n");
                content.append("INSERT INTO audit_withdraw_status (`id`, `billWithdrawId`, `auditType`, `auditStatus`, `auditDesc`, `verifyDesc`, `version`, `createDate`, `updateDate`, `description`, `isdeleted`) VALUES ('" + UUID.randomUUID().toString() + "', '" + id + "', 'MANUAL', 'WAIT_AUDIT', 'null', 'null', '4', " + currentDate + ", " + currentDate + ", NULL, '0');");
                content.append("\n");
            }
            FileUtil.genModuleTpl("E:/" + j + ".sql",content.toString());
        }
    }
}
