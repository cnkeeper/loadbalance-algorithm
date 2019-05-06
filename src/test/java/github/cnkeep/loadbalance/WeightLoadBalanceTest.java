package github.cnkeep.loadbalance;

import github.cnkeep.loadbalance.random.RandomLoadBalanceHandler;
import github.cnkeep.loadbalance.random.RandomNode;
import github.cnkeep.loadbalance.weight.WeightLoadBalanceHandler;
import github.cnkeep.loadbalance.weight.WeightNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述: 权重负载均衡测试
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/5/6
 */
public class WeightLoadBalanceTest {

    public static void main(String[] args) throws NotFindNodeException {
        LoadBalanceHandler loadBalanceHandler = new WeightLoadBalanceHandler();

        /**
         * 设置节点
         */
        final int nodeCount = 10;
        Random random = new Random();
        for (int index = 0; index < nodeCount; index++) {
            Node node = new WeightNode("node" + index, random.nextInt(10));
            loadBalanceHandler.addNode(node);
        }

        /**
         * 模拟请求
         */
        final int testCount = 100;
        List<Node> resultList = new LinkedList<>();
        for (int index = 0; index < testCount; index++) {
            Node node = loadBalanceHandler.getNode(null);
            resultList.add(node);
        }

        //输出所有节点信息
        loadBalanceHandler.getNodes().stream().forEach(System.out::println);
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
