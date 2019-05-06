package github.cnkeep.loadbalance.weight;


import github.cnkeep.loadbalance.Node;

import java.util.UUID;

/**
 * 描述： 带有权重的节点
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/4/28
 */
public class WeightNode implements Node<String> {
    /**
     * 配置的权重
     **/
    private int weight;
    /**
     * 当前的权重
     **/
    private int currentWeight;
    private String key;

    public WeightNode() {
        this(UUID.randomUUID().toString());
    }

    public WeightNode(String key) {
        this(key,5);
    }

    public WeightNode(String key, int weight) {
        this.key = key;
        this.weight = weight;
        this.currentWeight = weight;
    }

    @Override
    public String getKey() {
        return key;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WeightNode node = (WeightNode) o;

        return key.equals(node.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public String toString() {
        return "WeightNode{" +
                "weight=" + weight +
                ", currentWeight=" + currentWeight +
                ", key='" + key + '\'' +
                '}';
    }
}
