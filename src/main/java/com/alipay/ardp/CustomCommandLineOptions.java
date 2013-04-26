/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.alipay.ardp;

import com.sun.javadoc.RootDoc;

/**
 * 
 * @author ji.zhangyj
 * @version $Id: CustomCommandLineOptions.java, v 0.1 2013-4-24 ÏÂÎç8:49:45 ji.zhangyj Exp $
 */
public class CustomCommandLineOptions {

    public static boolean start(RootDoc root) {
        for(String[] arr: root.options()){
            for(String v: arr){
                System.out.println(arr+","+v);
            }
        }
//        System.out.println(root.options());
//        String tagName = readOptions(root.options());
//        System.out.println(tagName);
        //writeContents(root.classes(), tagName);
        return true;
    }

//    private static void writeContents(ClassDoc[] classes, String tagName) {
//        for (int i = 0; i < classes.length; i++) {
//            boolean classNamePrinted = false;
//            MethodDoc[] methods = classes[i].methods();
//            for (int j = 0; j < methods.length; j++) {
//                Tag[] tags = methods[j].tags(tagName);
//                if (tags.length > 0) {
//                    if (!classNamePrinted) {
//                        System.out.println("\n" + classes[i].name() + "\n");
//                        classNamePrinted = true;
//                    }
//                    System.out.println(methods[j].name());
//                    for (int k = 0; k < tags.length; k++) {
//                        System.out.println("   " + tags[k].name() + ": " + tags[k].text());
//                    }
//                }
//            }
//        }
//    }

    private static String readOptions(String[][] options) {
        String tagName = null;
        for (int i = 0; i < options.length; i++) {
            String[] opt = options[i];
            if (opt[0].equals("-tag")) {
                tagName = opt[1];
            }
        }
        return tagName;
    }

    public static int optionLength(String option) {
        System.out.println("optionLength.option="+option);
        if (option.equals("-foo")) {
            return 3;
        }
        return 0;
    }
//
//    public static boolean validOptions(String options[][], DocErrorReporter reporter) {
//        boolean foundTagOption = false;
//        for (int i = 0; i < options.length; i++) {
//            String[] opt = options[i];
//            if (opt[0].equals("-tag")) {
//                if (foundTagOption) {
//                    reporter.printError("Only one -tag option allowed.");
//                    return false;
//                } else {
//                    foundTagOption = true;
//                }
//            }
//        }
//        if (!foundTagOption) {
//            reporter.printError("Usage: javadoc -tag mytag -doclet ListTags ...");
//        }
//        return foundTagOption;
//    }
}
