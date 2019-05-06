package github.cnkeep.loadbalance;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 描述: 抽象类
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/5/6
 */
public abstract class AbstractBalanceTest {
    private LoadBalanceHandler nodeLoadBalanceHandler;

    public void test() throws Exception {
        nodeLoadBalanceHandler = getLoadBalanceHander().newInstance();

        /**
         * 设置节点
         */
        final int nodeCount = getNodeCount();
        addNode(nodeCount);

        /**
         * 模拟请求
         */
        int requestCount = getRequestCount();
        List<Node> resultList = simulateRequest(requestCount);

        //统计各个节点命中结果
        List<Map.Entry<Object, Long>> staticAynis = doStaticAynis(resultList);
        System.out.println(staticAynis);
    }

    private void addNode(int nodeCount) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (int index = 0; index < nodeCount; index++) {
            Node randomNode = getNodeType().getConstructor(String.class).newInstance("node"+index);
            nodeLoadBalanceHandler.addNode(randomNode);
        }
    }

    /**
     * 模拟请求
     *
     * @param reqestCount
     * @return
     * @throws NotFindNodeException
     */
    private List<Node> simulateRequest(int reqestCount) throws NotFindNodeException {
        List<Node> resultList = new LinkedList<>();
        for (int index = 0; index < reqestCount; index++) {
            Node node = nodeLoadBalanceHandler.getNode(null);
            resultList.add(node);
        }
        return resultList;
    }

    /**
     * 分组统计各个节点的命中情况
     *
     * @param resultList
     * @return List<Map.Entry<Object, Long>>
     */
    private List<Map.Entry<Object, Long>> doStaticAynis(List<Node> resultList) {
        Map<Object, Long> hintNodeStatic = resultList.stream().collect(Collectors.groupingBy(Node::getKey, Collectors.counting()));
        List<Map.Entry<Object, Long>> collect = hintNodeStatic.entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> ((String) e.getKey())))
                .collect(Collectors.toList());

        return collect;
    }

    /**
     * 获取负载均衡算法
     *
     * @return
     */
    protected abstract Class<? extends LoadBalanceHandler> getLoadBalanceHander();

    /**
     * 设置节点类型
     *
     * @return
     */
    protected abstract Class<? extends Node> getNodeType();

    /**
     * 设置模拟请求次数
     */
    public abstract int getRequestCount();

    /**
     * 设置节点个数
     *
     * @return
     */
    public abstract int getNodeCount();
}
