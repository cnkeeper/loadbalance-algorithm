package github.cnkeep.loadbalance.hash;

import github.cnkeep.loadbalance.LoadBalanceHandler;
import github.cnkeep.loadbalance.Node;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/4/28
 */
public abstract class AbstractHashLoadBalanceHandler<N extends Node<String>> implements LoadBalanceHandler<String, N> {
    /**
     * hash算法
     * @return
     */
    protected abstract HashFunction<String> hashFunction();
}
