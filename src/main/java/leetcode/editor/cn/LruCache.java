//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。 
//
// 
// 
// 实现 LRUCache 类： 
//
// 
// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
//限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。 
// 
//
// 
// 
// 
//
// 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？ 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 10000 
// 0 <= value <= 10⁵ 
// 最多调用 2 * 10⁵ 次 get 和 put 
// 
// Related Topics 设计 哈希表 链表 双向链表 👍 1787 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * title: 146 : LRU 缓存机制
 * since: 2021-12-19 14:04:32
 */
public class LruCache {
    public static void main(String[] args) {
//        LruCache.LRUCache solution = new LruCache.LRUCache();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 使用hashMap 和双端队列实现LRUCache
     */
    class LRUCache {
        int size;
        Map<Integer, Node> map;
        Node head, tail;

        class Node {
            int k, v;
            Node l, r;

            Node(int _k, int _v) {
                k = _k;
                v = _v;
            }
        }

        public LRUCache(int capacity) {
            size = capacity;
            map = new HashMap<>();
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.r = tail;
            tail.l = head;
        }

        /**
         * 获取：如果存在的话，更新下node(删除后，添加到队列头)
         */
        public int get(int key) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                refresh(node);
                return node.v;
            }
            return -1;
        }

        /**
         * 更新：如果存在的话更新节点值；
         * 如果不存在的话，判断当前容量大小，如果满了则删除队列尾部后再添加，否则直接添加
         *
         * @param key
         * @param value
         */
        public void put(int key, int value) {
            Node node;
            if (map.containsKey(key)) {
                node = map.get(key);
                node.v = value;
            } else {
                if (map.size() == size) {
                    Node del = tail.l;
                    map.remove(del.k);
                    delete(del);
                }
                node = new Node(key, value);
                map.put(key, node);
            }
            // 最后更新下位置
            refresh(node);
        }

        private void delete(Node del) {
            if (del.l != null) {
                Node left = del.l;
                left.r = del.r;
                del.r.l = left;
            }
        }

        private void refresh(Node node) {
            delete(node);
            node.r = head.r;
            node.l = head;

            head.r.l = node;
            head.r = node;
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}