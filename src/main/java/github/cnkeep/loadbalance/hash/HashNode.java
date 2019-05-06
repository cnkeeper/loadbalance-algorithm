package github.cnkeep.loadbalance.hash;


import github.cnkeep.loadbalance.Node;

import java.util.UUID;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/4/28
 */
public class HashNode implements Node<String> {
    protected String key;

    public HashNode() {
        this.key = UUID.randomUUID().toString();
    }

    public HashNode(String key) {
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

        HashNode hashNode = (HashNode) o;

        return key.equals(hashNode.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public String toString() {
        return "HashNode{" +
                "key='" + key + '\'' +
                '}';
    }
}
