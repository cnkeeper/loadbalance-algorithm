package github.cnkeep.loadbalance.hash.hashfunction;

import github.cnkeep.loadbalance.hash.HashFunction;

/**
 * 描述: FNV1 hash算法
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/4/28
 */
public class FNV1HashFunction implements HashFunction<String> {
    @Override
    public Long hash(String key) {
        final int p = 16777619;
        long hash = 2166136261L;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash ^ key.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return hash;
    }
}
