package github.cnkeep.loadbalance;

/**
 * 描述: 节点对象，key属性用于标识一个节点，不同节点的key应当不同
 * Attention：重写hashCode和equals方法
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/4/28
 */
public interface Node<K> {
    /**
     * 获取节点用于计算hash值的key
     *
     * @return
     */
    K getKey();
}
