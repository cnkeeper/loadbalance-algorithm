package github.cnkeep.loadbalance.polling;


import github.cnkeep.loadbalance.LoadBalanceHandler;
import github.cnkeep.loadbalance.Node;
import github.cnkeep.loadbalance.NotFindNodeException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述: 轮询方式的负载均衡算法
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/4/28
 */
public class PollingLoadBalanceHandler implements LoadBalanceHandler<String, PollingNode> {
    private final static AtomicInteger counter = new AtomicInteger(0);

    private final List<PollingNode> nodesList = new ArrayList<>();

    @Override
    public void addNode(PollingNode node) {
        nodesList.add(node);
    }

    @Override
    public boolean removeNode(PollingNode node) {
        return nodesList.remove(node);
    }

    @Override
    public PollingNode getNode(String key) throws NotFindNodeException {
        return nodesList.get(next());
    }

    @Override
    public List<PollingNode> getNodes() {
        return this.nodesList;
    }

    private int next() {
        synchronized (counter) {
            int next = counter.getAndIncrement();
            if (next < 0) {
                counter.set(0);
                next = 0;
            }
            return nodesList.size() % next;
        }
    }
}
