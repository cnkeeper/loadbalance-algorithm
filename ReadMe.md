负载均衡算法的简单实现
----------------------

## 介绍
本项目目的是学习各种负载均衡算法的实现机理，不推荐用于线上环境。   

## 负载均衡算法

+ 轮询算法  
+ 随机算法  
+ 权重算法  
+ hash算法  
+ 一致性hash算法  

## 目录结构
```text
└─loadbalance
    |  #外部接口
    │  LoadBalanceHandler.java
    │  Node.java
    │  NotFindNodeException.java
    │
    ├─hash  #hash负载均衡算法
    │  │  AbstractHashLoadBalanceHandler.java
    │  │  DefaultHashLoadBalanceHandler.java
    │  │  HashFunction.java
    │  │  HashNode.java
    │  │
    │  ├─consistent #一致性hash算法
    │  │      AbstractConsistentHashLoadBalanceHandler.java
    │  │      DefaultConsistentHashLoadBalanceHandler.java
    │  │      DistributeNode.java
    │  │
    │  └─hashfunction #默认提供的hash算法
    │          FNV1HashFunction.java
    │          MD5HashFunction.java
    │          Murmur2HashFunction.java
    │
    ├─polling #轮询算法
    │      PollingLoadBalanceHandler.java
    │      PollingNode.java
    │
    ├─random  #随机算法
    │      RandomLoadBalanceHandler.java
    │      RandomNode.java
    │
    └─weight  #权重算法
            WeightLoadBalanceHandler.java
            WeightNode.java
```