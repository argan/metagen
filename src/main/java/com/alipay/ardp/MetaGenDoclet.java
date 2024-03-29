/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.alipay.ardp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.LanguageVersion;
import com.sun.javadoc.RootDoc;

/**
 * 收集Bean的原始信息，按依赖关系排序，并生成相应的元数据配置信息
 * 
 * @author kerrigan
 * @author ji.zhangyj
 * 
 * @version $Id: MetaGenDoclet.java, v 0.1 2013-4-24 下午8:08:01 ji.zhangyj Exp $
 */
public class MetaGenDoclet {
    public static boolean start(RootDoc root) {
        ClassDoc[] classes = root.classes();
        Set<Field> fields = new HashSet<Field>();
        Map<String, Model> map = new HashMap<String, Model>();

        for (int i = 0; i < classes.length; ++i) {
            Model model = new Model(classes[i], map);
            map.put(model.name, model);

            fields.addAll(model.fields);
        }
        TopologicalSorter<Model> sorter = new TopologicalSorter<Model>(map.values(), false);
        sorter.sort();

        List<Model> models = sorter.getSorted();

        String outputFileName = readOptions(root.options());
        System.out.println("outputFileName=" + outputFileName);
        BufferedWriter writer = null;
        try {
            if (outputFileName == null || "".equals(outputFileName)) {
                root.printError("Not specify output template file name!");
            }

            writer = new BufferedWriter(new FileWriter(outputFileName));

            for (Field f : fields) {
                System.out.println(f);
                writer.write(f.toString());
            }
            for (Model m : models) {
                System.out.println(m);
                writer.write(m.toString());
            }
        } catch (IOException e) {
            root.printError("Write template file error,fileName=" + outputFileName);
        } finally {
            if (writer != null) {
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    root.printError("Write close error,fileName=" + outputFileName);
                }
            }
        }

        return true;
    }

    private static String readOptions(String[][] options) {
        String tagName = null;
        for (int i = 0; i < options.length; i++) {
            String[] opt = options[i];
            if (opt[0].equals("-tpl")) {
                tagName = opt[1];
            }
        }
        return tagName;
    }

    public static int optionLength(String option) {
        if (option.equals("-tpl")) {
            return 2;
        }
        return 0;
    }

    public static LanguageVersion languageVersion() {
        return LanguageVersion.JAVA_1_5;
    }
    
}
