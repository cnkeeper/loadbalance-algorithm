package github.cnkeep.loadbalance;

import github.cnkeep.loadbalance.random.RandomLoadBalanceHandler;
import github.cnkeep.loadbalance.random.RandomNode;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 描述: 随机算法测试类
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/5/6
 */
public class RandomTest {
    public static void main(String[] args) throws NotFindNodeException {
        LoadBalanceHandler randomNodeLoadBalanceHandler = new RandomLoadBalanceHandler();

        /**
         * 设置节点
          */
        final int nodeCount = 10;
        for (int index = 0; index < nodeCount; index++) {
            Node randomNode = new RandomNode("node" + index);
            randomNodeLoadBalanceHandler.addNode(randomNode);
        }

        /**
         * 模拟请求
         */
        final int testCount = 100;
        List<Node> resultList = new LinkedList<>();
        for (int index = 0; index < testCount; index++) {
            Node node = randomNodeLoadBalanceHandler.getNode(null);
            resultList.add(node);
        }

        //输出所有节点信息
        randomNodeLoadBalanceHandler.getNodes().stream().forEach(System.out::println);

        /**
         * 分组统计各个节点的命中情况
         */
        Map<Object, Long> hintNodeStatic = resultList.stream().collect(Collectors.groupingBy(Node::getKey, Collectors.counting()));
        List<Map.Entry<Object, Long>> collect = hintNodeStatic.entrySet()
                                                                .stream()
                                                                .sorted(Comparator.comparing(e -> ((String) e.getKey())))
                                                                .collect(Collectors.toList());

        System.out.println(collect);
    }
}
