package github.cnkeep.loadbalance.hash.consistent;


import github.cnkeep.loadbalance.NotFindNodeException;
import github.cnkeep.loadbalance.hash.AbstractHashLoadBalanceHandler;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述: 抽象一致性hash算法，ThreadSafe
 * 参考：https://www.cnblogs.com/moonandstar08/p/5405991.html
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/4/28
 */
public abstract class AbstractConsistentHashLoadBalanceHandler<N extends DistributeNode> extends AbstractHashLoadBalanceHandler<N> {
    /**
     * 默认为每个节点创建2个虚拟节点
     **/
    private final static int DEFAULT_VIRTUAL_NODE_COUNT = 2;

    /**
     * 每个节点的虚拟节点数量
     **/
    protected int virtualNodeCount = DEFAULT_VIRTUAL_NODE_COUNT;

    /**
     * 环形节点
     **/
    private SortedMap<Long, N> circleNodes = new TreeMap<>();

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void addNode(N node) {
        Lock writeLock = this.lock.writeLock();
        writeLock.lock();
        try {
            for (int count = 0; count < virtualNodeCount; count++) {
                String virtualKey = node.getVirtualKey();
                circleNodes.put(hashFunction().hash(virtualKey), node);
            }
            circleNodes.put(hashFunction().hash(node.getKey()), node);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean removeNode(N node) {
        Lock writeLock = this.lock.writeLock();
        writeLock.lock();
        try {
            for (int count = 0; count < virtualNodeCount; count++) {
                circleNodes.remove(hashFunction().hash(node.getVirtualKey()));
            }
            circleNodes.remove(hashFunction().hash(node.getKey()));
        } finally {
            writeLock.unlock();
        }
        return true;
    }

    @Override
    public N getNode(String key) throws NotFindNodeException {
        if (circleNodes.isEmpty()) {
            throw new NotFindNodeException("can find a available node for key=" + key);
        }

        N node;
        Long hash = hashFunction().hash(key);

        Lock readLock = this.lock.readLock();
        readLock.lock();
        try {
            if (!circleNodes.containsKey(hash)) {
                //未命中，寻找顺时针第一个临近节点
                SortedMap<Long, N> tailMap = circleNodes.tailMap(hash);
                hash = tailMap.isEmpty() ? circleNodes.firstKey() : tailMap.firstKey();
            }

            //刚好命中节点
            node = circleNodes.get(hash);
        } finally {
            readLock.unlock();
        }
        return node;
    }

    /**
     * 自定义每个节点的虚拟节点个数
     *
     * @return
     */
    protected abstract int virtualNodeCount();

    @Override
    public List<N> getNodes() {
        return new LinkedList<>(circleNodes.values());
    }
}
