import com.baomidou.mybatisplus.generator.FastAutoGenerator;

import java.util.Collections;


public class DemoApplication {

    public static void main55544445(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://10.0.41.145:3306/openresty_china?useSSL=false&autoReconnect=true&characterEncoding=utf8", "root", "poembro")
                .globalConfig(builder ->
                        builder.author("poembro") // 设置作者
                                .fileOverride() // 覆盖已生成文件
                                .enableSwagger() // 开启 swagger 模式
                                .outputDir("/Users/luoyuxiang/Documents/www/java/openresty-china/dao/src/main/java/") // 指定输出目录
                )
                .packageConfig(builder ->
                        builder.parent("com.openresty") // 设置父包名
                                .moduleName("dao") // 设置父包模块名
                                //.pathInfo(Collections.singletonMap(OutputFile.xml, "D://")); // 设置mapperXml生成路径
                )
                .strategyConfig(builder ->
                        builder.addInclude("user", "topic", "notification", "like", "follow", "comment", "collect", "category")
                )
                .execute();
    }
}
