package github.cnkeep.loadbalance.hash.hashfunction;


import github.cnkeep.loadbalance.hash.HashFunction;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 描述: MD5 hash算法
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/4/28
 */
public class MD5HashFunction implements HashFunction<String> {
    @Override
    public Long hash(String key) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(key.getBytes());
            byte[] bKey = md5.digest();

            long res = ((long) (bKey[3] & 0xFF) << 24)
                    | ((long) (bKey[2] & 0xFF) << 16)
                    | ((long) (bKey[1] & 0xFF) << 8)
                    | (long) (bKey[0] & 0xFF);
            return res;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}
