package org.wsh.common.test.sql;

import org.wsh.common.test.model.dto.UserBasicDO;
import org.wsh.common.util.file.FileUtil;
import org.wsh.common.util.shiro.encrypt.HashPwd;

import java.io.IOException;
import java.util.UUID;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-09-21 10:17
 */
public class CreateInsert {

    public static void main(String[] args) throws IOException {

//
//for(int i = 1; i < 30; i++){
//            StringBuffer content = new StringBuffer();
//            int minNum = 1000000 * i;
//            int maxNum = 1000000 * (i + 1);
//            System.out.println("minNum=>" + minNum + " maxNum=>" + maxNum);
//            addUser(content, minNum, maxNum);
//            FileUtil.genModuleTpl("E:/insert_user_" + i + ".sql", content.toString());
//        }
//        for(int i = 1; i < 30; i++){
//            StringBuffer content = new StringBuffer();
//            int minNum = 1000000 * i;
//            int maxNum = 1000000 * (i + 1);
//            System.out.println("minNum=>" + minNum + " maxNum=>" + maxNum);
//            addUserRole(content, minNum, maxNum);
//            FileUtil.genModuleTpl("E:/insert_user_role_" + i + ".sql", content.toString());
//        }

        for(int i = 1; i < 30; i++){
            System.out.println("source E:/SQL/insert_user_" + i + ".sql;");
        }

        for(int i = 1; i < 30; i++){
            System.out.println("source E:/SQL/insert_user_role_" + i + ".sql;");
        }
    }

    private static void addUserRole(StringBuffer content, int minNum, int maxNum) {
        content.append("INSERT INTO user_role (id,user_id, role_id, create_user_id, gmt_created, gmt_modified) VALUES");
        content.append("\n");
        for (int i = minNum; i < maxNum; i++) {
            String id = UUID.randomUUID().toString();
            if (i == maxNum -1){
                content.append("(").append(i).append(",").append(i).append(", 2, 1,NOW(),NOW());");
            }else{
                content.append("(").append(i).append(",").append(i).append(", 2, 1,NOW(),NOW()),");
                content.append("\n");
            }

        }
        content.append("\n");
    }

    private static void addUser(StringBuffer content, int minNum, int maxNum) {
        content.append("INSERT INTO user_basic (id,user_name, password, salt, real_name, face_url, phone, email, status, gmt_created, gmt_modified) VALUES");
        content.append("\n");
        for (int i = minNum; i < maxNum; i++) {
            String id = UUID.randomUUID().toString();
            if (i == maxNum -1){
                content.append("(").append(i).append(",").append("'用户").append(i).append("', 'a22afffd9237b3c9b7ef4a9ecf88d106392cfc33', '3e731f4fd71db231', '模拟用户', null, '12345678911', '123456@qq.com', 0,NOW(),NOW());");
            }else{
                content.append("(").append(i).append(",").append("'用户").append(i).append("', 'a22afffd9237b3c9b7ef4a9ecf88d106392cfc33', '3e731f4fd71db231', '模拟用户', null, '12345678911', '123456@qq.com', 0,NOW(),NOW()),");
                content.append("\n");
            }

        }
        content.append("\n");
    }

}
