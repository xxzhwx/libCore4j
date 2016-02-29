package com.xxzhwx.core.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public final class ClassUtils {
    /**
     * 获取指定 Class 所在目录及其子目录下的所有 Class 的名字
     */
    public static List<String> listClassNames(Class<?> cls) {
        String pkgName = cls.getPackage().getName();

        String clsName = cls.getSimpleName() + ".class";
        String clsPath = cls.getResource(clsName).getPath();
        File file = new File(clsPath).getParentFile();

        List<String> classNames = new ArrayList<>();
        scans(pkgName, file.getAbsolutePath(), classNames);

        return classNames;
    }

    private static void scans(String packageName, String rootPath, List<String> classNames) {
        File file = new File(rootPath);
        File[] files = file.listFiles();
        if (files == null) {
            return;
        }

        for (File f : files) {
            if (f.isFile()) {
                String className = new StringJoiner(".")
                        .add(packageName)
                        .add(f.getName().split("\\.")[0])
                        .toString();
                classNames.add(className);
                continue;
            }

            if (f.isDirectory()) {
                String pkgName = new StringJoiner(".")
                        .add(packageName)
                        .add(f.getName())
                        .toString();
                scans(pkgName, f.getAbsolutePath(), classNames);
            }
        }
    }
}
