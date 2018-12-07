package com.algorithm.data.string;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * https://www.cnblogs.com/cmmdc/p/7337611.html
 */
public class Trie {

    private TrieNode root;

    /**
     * 初始化字典树
     * 根节点没有val值
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * 批量插入关键词
     */
    public void addKeyWord(String... keyWords) {
        for (String keyWord : keyWords) {
            insert(keyWord);
        }
        print(root);
        buildFailure();
    }


    /**
     * 建立字典树,在字典树中插入一个单词
     */
    private void insert(String keyWord) {
        if (StringUtils.isEmpty(keyWord)) {
            return;
        }
        char[] letters = keyWord.toCharArray();
        TrieNode curNode = root;
        TrieNode child;
        for (char letter : letters) {
            child = curNode.childOf(letter);
            if (child == null) {
                child = new TrieNode(letter);
                //如果当前节点的儿子节点中没有该字符，则构建一个TrieNode并添加的childMap中
                curNode.addChild(child);
            } else {
                //如果已经存在，则将由根至该儿子节点组成的字符串模式出现的次数+1
                child.num++;
            }
            // 构造子节点完成，递进，准备去构造孙子节点
            curNode = child;
        }
        curNode.isEnd = true;
        curNode.keyWord = keyWord;
    }

    /**
     * 建立failure表
     * 1.根节点的孩子，fail一定是指向根
     * 2.设置一个节点curNode的fail指向，需要知道parent.fail，如果parent.fail的孩子节点中有相同的节点parent.child，那么curNode.fail=parent.child
     * 3.如果没有，则parent.fail=parent.fail.fail往上递进。
     * 4.直到空节点还没有找到类似的节点，那么curNode.fail=root
     */
    private void buildFailure() {
        Queue<TrieNode> queue = new LinkedList<>();
        TrieNode curNode = root;
        // 根节点的孩子入队
        for (Map.Entry<Character, TrieNode> entry : curNode.children.entrySet()) {
            // 根节点的孩子fail节点都应当指向root
            entry.getValue().failure = root;
            queue.offer(entry.getValue());
        }
        // 广度优先搜索字典树
        while (!queue.isEmpty()) {
            curNode = queue.poll();
            for (TrieNode child : curNode.getChildren()) {
                queue.offer(child);
                // failNode=当前节点指向的失败节点
                TrieNode failNode = curNode.failure;
                while (failNode != null) {
                    TrieNode node = failNode.childOf(child.val);
                    // 失败节点子节点包含当前节点子节点
                    // 需要注意的是，failure一定是指向的一个拥有相同节点的字符或者根节点
                    if (node != null) {
                        child.failure = node;
                        break;
                    }
                    failNode = failNode.failure;
                }
                // 失败节点指向空，说明递进到根节点
                if (failNode == null) {
                    child.failure = root;
                }
            }
        }
    }

    /**
     * 用字典从文本中搜索，并返回文本和字典树都有的词
     * 1.设置当前节点curNode为根节点
     * 2.将字符与curNode.children匹配
     * 3.匹配失败，curNode=curNode.fail递进，字符指针i不动(如果是已经到追溯到根节点，重置指针curNode，结束此次循环，在下一个字符(i++)上重新开始匹配)
     * 4.匹配成功，此时需要检查有两种情况
     *   a.检查是否到达当前子树终点
     *   b.检查是否到达另外子树的终点(关键词中间部分的字符串可能正好包含另一个关键词)
     * 5.匹配成功后，还要继续往下递进，看当前子树上是否是有另外的终点
     */
    public List<String> searchKeyWord(String data) {
        List<String> ret = new ArrayList<>();
        int length = data.length();
        TrieNode curNode = root;
        int i = 0;
        while(i < length){
            // 始终拿curNode.children与字符匹配
            TrieNode target = curNode.childOf(data.charAt(i));
            // 未匹配上，curNode跳转到fail，i不变，continue
            if (target == null) {
                curNode = curNode.failure;
                // 注意如果curNode节点已经是根节点（根节点的fail为空）
                // 说明此次匹配失败，curNode重置，在下一个字符(i++)上重新开始匹配
                if (curNode == null) {
                    curNode = root;
                    i++;
                }
                continue;
            }
            // 匹配成功，检查是否是一个终点
            if (target.isEnd) {
                ret.add(target.keyWord);
            }
            // 关键词中间部分的字符串可能正好包含另一个关键词
            // 也就是说当前子树下还没有找到终点，但是另外一个子树有可能已经到终点
            if (target.failure != null && target.failure.isEnd) {
                ret.add(target.failure.keyWord);
            }
            curNode = target;
            i++;
        }
        return ret;
    }

    /**
     * 字典树节点
     */
    static class TrieNode {
        /**
         * 节点的值
         */
        private char val;
        /**
         * 所有的儿子节点
         */
        private Map<Character, TrieNode> children = new HashMap<>();
        /**
         * 是不是最后一个节点
         */
        private boolean isEnd = false;
        /**
         * 匹配失败，跳转的位置，与next表类似
         */
        private TrieNode failure;
        /**
         * 有多少单词通过这个节点,即由根至该节点组成的字符串模式出现的次数
         */
        private int num = 1;
        /**
         * 该节点表示的关键词，当isEnd == false，该值为空
         */
        private String keyWord;

        public TrieNode() {
        }

        public TrieNode(char val) {
            this.val = val;
        }

        void addChild(TrieNode child) {
            children.put(child.val, child);
        }

        TrieNode childOf(char letter) {
            return children.get(letter);
        }

        Collection<TrieNode> getChildren() {
            return children.values();
        }

        @Override
        public String toString() {
            return "TrieNode{" +
                    "val=" + val +
                    ", failure=" + failure +
                    '}';
        }
    }

    private void print(TrieNode root) {
        Queue<TrieNode> queue = new LinkedList<>();
        int levelCount = root.getChildren().size();
        int nextLevelCount = 0;
        for (TrieNode node : root.getChildren()) {
            queue.add(node);
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            TrieNode node = queue.poll();
            sb.append(node.val).append("\t");
            levelCount--;
            for (TrieNode child : node.getChildren()) {
                queue.offer(child);
                nextLevelCount++;
            }
            if (levelCount <= 0) {
                sb.append("\n");
                levelCount = nextLevelCount;
                nextLevelCount = 0;
            }
        }
        System.out.println(sb.toString());
    }

    public static void testPrint() {
        Trie.TrieNode root = new Trie.TrieNode('1');
        TrieNode left = new TrieNode('2');
        TrieNode right = new TrieNode('3');
        root.addChild(left);
        root.addChild(right);
        TrieNode left1 = new TrieNode('4');
        TrieNode right1 = new TrieNode('5');
        left.addChild(left1);
        left.addChild(right1);
        TrieNode left2 = new TrieNode('6');
        TrieNode right2 = new TrieNode('7');
        right.addChild(left2);
        right.addChild(right2);
        Trie trie = new Trie();
        trie.print(root);
    }

    public static void main(String[] args) {
//        testPrint();


//        String[] keywords = new String[]{"我是好人","我是坏人","好人","坏人","世界","那么大","世界那么大","大"};
        String[] keywords = new String[]{"我是好人", "我是坏人", "好天", "坏人"};
        String data = "我是好人吗?这事需要问问自己,人能分成好天人坏人吗?这恐怕谁也无法解答.世界那么大,给你的想法那么大,我们世界里,只能想想大而已,我是好儿童好好天";
//        String data = "我是好儿童好好天";
        Trie trie = new Trie();
        trie.addKeyWord(keywords);
        List<String> res = trie.searchKeyWord(data);
        System.out.println(JSONObject.toJSONString(res));

    }
}
