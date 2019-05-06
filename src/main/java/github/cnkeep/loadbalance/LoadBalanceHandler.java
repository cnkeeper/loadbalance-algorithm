package github.cnkeep.loadbalance;

import java.util.List;

/**
 * 描述: 负载均衡接口
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/4/28
 */
public interface LoadBalanceHandler<K,N extends Node<K>> {
    /**
     * 新增节点
     * @param node
     */
    void addNode(N node);

    /**
     * 移除节点
     * @param node
     * @return
     */
    boolean removeNode(N node);

    /**
     *
     * @param key
     * @return
     * @throws
     */
    N getNode(K key) throws NotFindNodeException;

    /**
     * 获取当前所有节点信息
     * @return
     */
    List<N> getNodes();
}
