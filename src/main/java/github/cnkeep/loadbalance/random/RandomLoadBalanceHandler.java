package github.cnkeep.loadbalance.random;

import github.cnkeep.loadbalance.LoadBalanceHandler;
import github.cnkeep.loadbalance.NotFindNodeException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 描述: 随机负载均衡算法
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/4/28
 */
public class RandomLoadBalanceHandler implements LoadBalanceHandler<String, RandomNode> {
    private final List<RandomNode> nodesList = new ArrayList<>();
    @Override
    public void addNode(RandomNode node) {
        nodesList.add(node);
    }

    @Override
    public boolean removeNode(RandomNode node) {
        return nodesList.remove(node);
    }

    @Override
    public RandomNode getNode(String key) throws NotFindNodeException {
        return nodesList.get(ThreadLocalRandom.current().nextInt(nodesList.size()));
    }

    @Override
    public List<RandomNode> getNodes() {
        return this.nodesList;
    }
}
