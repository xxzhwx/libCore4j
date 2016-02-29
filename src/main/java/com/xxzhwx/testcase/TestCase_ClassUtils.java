package com.xxzhwx.testcase;

import com.xxzhwx.App;
import com.xxzhwx.core.utils.ClassUtils;

import java.util.List;
import java.util.StringJoiner;


public class TestCase_ClassUtils implements TestCase {
    @Override
    public boolean run() {
        List<String> names = ClassUtils.listClassNames(App.class);

        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("class names:");
        names.forEach(joiner::add);

        System.out.println("" + joiner.toString());
        System.out.println("class size: " + names.size());
        return true;
    }
}
