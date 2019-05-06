package github.cnkeep.loadbalance.hash.consistent;


import github.cnkeep.loadbalance.hash.HashNode;

import java.util.UUID;

/**
 * 描述: 分布式节点
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/4/28
 */
public class DistributeNode extends HashNode {
    private String virtualKey;

    public DistributeNode() {
        this.key = UUID.randomUUID().toString();
        this.virtualKey = this.key + System.currentTimeMillis();
    }

    public DistributeNode(String key) {
        super(key);
        this.virtualKey = key + System.currentTimeMillis();
    }

    public DistributeNode(String key, String virtualKey) {
        super(key);
        this.virtualKey = virtualKey;
    }

    /**
     * 获取虚拟节点用于计算hash值的key
     *
     * @return
     */
    public String getVirtualKey() {
        return virtualKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) return false;

        DistributeNode that = (DistributeNode) o;

        return virtualKey.equals(that.virtualKey);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + virtualKey.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DistributeNode{" +
                "virtualKey='" + virtualKey + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
