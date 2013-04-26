/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.alipay.ardp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.sun.tools.javadoc.Main;

/**
 * Metadata define template gengerator
 * 
 * @goal metagen
 * 
 * @phase process-sources
 * 
 * @author ji.zhangyj
 * @version $Id: MetadataDefineTemplateMojo.java, v 0.1 2013-4-24 下午3:07:09 ji.zhangyj Exp $
 */
public class MetaGenMojo extends AbstractMojo {

    /**
     * Java package names
     * @parameter
     * @required
     */
    private List<String> packageNames = new ArrayList<String>();

    /**
     * Project source file directory
     * @parameter expression="${project.build.sourceDirectory}"
     * @required
     */
    private String       sourceDirectory;

    /**
     * build directory
     * @parameter expression="${project.build.directory}"
     * @required
     */
    private String       directory;

    /** 
     * @see org.apache.maven.plugin.Mojo#execute()
     */
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("Hello，my first mojo~");

        for (String packageName : packageNames) {
            String srcDir = sourceDirectory + "/" + packageName.replace(".", "/") + "/";
            File fileDir = new File(srcDir);
            if (fileDir != null && fileDir.isDirectory() && fileDir.exists()) {
                Main.execute("javadoc", MetaGenDoclet.class.getName(), new String[] { "-private",
                        "-sourcepath", sourceDirectory,//源文件位置
                        "-subpackages", packageName,//要处理的package name
                        "-encoding","utf-8",
                        "-tpl", srcDir + "metadata-def.txt", });

                System.out.println("project.build.sourceDirectory=" + this.sourceDirectory
                                   + ",project.build.directory=" + this.directory);
            }
        }
    }

    public List<String> getPackageNames() {
        return packageNames;
    }

    public void setPackageNames(List<String> packageNames) {
        this.packageNames = packageNames;
    }

    public String getSourceDirectory() {
        return sourceDirectory;
    }

    public void setSourceDirectory(String sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
}
