package github.cnkeep.loadbalance.weight;

import github.cnkeep.loadbalance.LoadBalanceHandler;
import github.cnkeep.loadbalance.NotFindNodeException;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: 加权负载均衡算法
 *
 * <pre>
 *     参考：https://blog.csdn.net/gqtcgq/article/details/52076997
 *     1. 每个节点包含2个权重值，weight:配置的权重值，currentWeight:当前的权重值
 *     2. 每次选择当前权重最大的
 *     3. 每次当请求到来，选取服务器时，会遍历数组中所有服务器。对于每个服务器，让它的current_weight增加它的weight；同时累加所有服务器的weight，并保存为total。
 * 　　遍历完所有服务器之后，如果该服务器的current_weight是最大的，就选择这个服务器处理本次请求。最后把该服务器的current_weight减去total。
 * </pre>
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/4/28
 */
public class WeightLoadBalanceHandler implements LoadBalanceHandler<String,WeightNode> {
    private final List<WeightNode> nodesList = new ArrayList<>();

    /**
     * 每次当请求到来，选取服务器时，会遍历数组中所有服务器。对于每个服务器，让它的current_weight增加它的weight；同时累加所有服务器的weight，并保存为total。
     * 遍历完所有服务器之后，如果该服务器的current_weight是最大的，就选择这个服务器处理本次请求。最后把该服务器的current_weight减去total。
     * @return
     */
    public synchronized int next(){
        int max=0;
        int maxIndex=0;
        int total = 0;
        for (int index=0;index<nodesList.size();index++){
            WeightNode node = nodesList.get(index);
            int currentWeight = node.getCurrentWeight();
            int weight = node.getWeight();
            currentWeight += weight;
            node.setCurrentWeight(currentWeight);
            if(max<currentWeight){
                max = currentWeight;
                maxIndex = index;
            }
            total += node.getWeight();
        }

        WeightNode selectedNode = nodesList.get(maxIndex);
        int currentWeight = selectedNode.getCurrentWeight();
        selectedNode.setCurrentWeight(currentWeight-total);
        return maxIndex;
    }

    @Override
    public void addNode(WeightNode node) {
        nodesList.add(node);
    }

    @Override
    public boolean removeNode(WeightNode node) {
        nodesList.remove(node);
        return true;
    }

    @Override
    public WeightNode getNode(String key) throws NotFindNodeException {
        return nodesList.get(next());
    }

    @Override
    public List<WeightNode> getNodes() {
        return this.nodesList;
    }
}
