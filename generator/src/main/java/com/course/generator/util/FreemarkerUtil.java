package com.course.generator.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/*
 * @ProjectName: course
 * @Package: com.course.generator.util
 * @ClassName: FreemarkerUtil
 * @Author: 游佳琪
 * @Description: 模板生成类
 * @Date: 2020-10-31 09:25
 * @Version: 1.0
 */
    public class FreemarkerUtil {

        static String ftlPath = "generator\\src\\main\\java\\com\\course\\generator\\ftl\\";

        static Template temp;

        /*
         * 生成ftl模板
         * @Author: YJQ
         * @Date: 2020-10-31 09:27
         */

        public static void initConfig(String ftlName) throws IOException {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
            cfg.setDirectoryForTemplateLoading(new File(ftlPath));
            cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_29));
            temp = cfg.getTemplate(ftlName);
        }
        /*
         * 根据模板生成生成器
         * @Author: YJQ
         * @Date: 2020-10-31 09:27
         */
        public static void generator(String fileName,Map<String, Object> map) throws IOException, TemplateException {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            temp.process(map, bw);
            bw.flush();
            fw.close();
        }
    }
