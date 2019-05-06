package github.cnkeep.loadbalance.hash;

/**
 * 描述: 自定义Hash算法接口
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/4/28
 */
@FunctionalInterface
public interface HashFunction<K> {
    /**
     * 自定义hash算法
     * @param key
     * @return
     */
    Long hash(K key);
}
