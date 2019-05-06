package github.cnkeep.loadbalance.hash;

import github.cnkeep.loadbalance.NotFindNodeException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/4/28
 */
public class DefaultHashLoadBalanceHandler extends AbstractHashLoadBalanceHandler<HashNode> {
    private final static int DEFAULT_NODE_SIZE = 32;
    private final ConcurrentMap<Long, HashNode> hashNodes = new ConcurrentHashMap<>(DEFAULT_NODE_SIZE);
    private volatile long nodeCount = DEFAULT_NODE_SIZE;

    public DefaultHashLoadBalanceHandler() {
    }

    @Override
    public void addNode(HashNode node) {
        hashNodes.put(kHashFunction.hash(node.getKey()), node);
        setNodeCount();
    }

    @Override
    public boolean removeNode(HashNode node) {
        hashNodes.remove(kHashFunction.hash(node.getKey()));
        setNodeCount();
        return true;
    }

    @Override
    public HashNode getNode(String key) throws NotFindNodeException {
        Long hash = kHashFunction.hash(key);
        return hashNodes.get(hash);
    }

    @Override
    public List<HashNode> getNodes() {
        return new LinkedList<>(hashNodes.values());
    }

    private final HashFunction<String> kHashFunction = (node) -> {
        int hash = node.hashCode();
        return nodeCount % hash;
    };

    private void setNodeCount() {
        nodeCount = hashNodes.size();
    }

    @Override
    protected HashFunction<String> hashFunction() {
        return kHashFunction;
    }
}
