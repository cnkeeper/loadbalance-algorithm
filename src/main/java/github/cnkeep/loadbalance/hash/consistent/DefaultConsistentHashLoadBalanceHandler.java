package github.cnkeep.loadbalance.hash.consistent;

import github.cnkeep.loadbalance.hash.HashFunction;
import github.cnkeep.loadbalance.hash.hashfunction.MD5HashFunction;

/**
 * 描述: 一致性hash算法默认实现
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/4/28
 */
public class DefaultConsistentHashLoadBalanceHandler extends AbstractConsistentHashLoadBalanceHandler<DistributeNode> {
    private final int defaultVirtualNodeCount = 5;

    @Override
    protected HashFunction<String> hashFunction() {
        return new MD5HashFunction();
    }

    @Override
    protected int virtualNodeCount() {
        return defaultVirtualNodeCount;
    }
}
