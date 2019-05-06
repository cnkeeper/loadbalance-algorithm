package github.cnkeep.loadbalance.polling;


import github.cnkeep.loadbalance.Node;

import java.util.UUID;

/**
 * 描述： 随机节点
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/4/28
 */
public class PollingNode implements Node<String> {

    private String key;

    public PollingNode() {
        this.key = UUID.randomUUID().toString();
    }

    public PollingNode(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PollingNode that = (PollingNode) o;

        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public String toString() {
        return "RandomNode{" +
                "key='" + key + '\'' +
                '}';
    }
}
