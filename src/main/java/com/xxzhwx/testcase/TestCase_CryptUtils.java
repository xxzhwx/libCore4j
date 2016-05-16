package com.xxzhwx.testcase;

import com.xxzhwx.core.utils.crypt.CryptUtils;
import com.xxzhwx.core.utils.crypt.Md5Utils;

public class TestCase_CryptUtils implements TestCase {
    @Override
    public boolean run() {
        String str = "www.google.com啊哦呃";
        System.out.println("str: " + str);

        String encryptStr = CryptUtils.encrypt(str);
        System.out.println("encryptStr: " + encryptStr);

        String decryptStr = CryptUtils.decrypt(encryptStr);
        System.out.println("decryptStr: " + decryptStr);

        String md5Str = Md5Utils.gen(str);
        System.out.println("md5Str: " + md5Str);
        return true;
    }
}
