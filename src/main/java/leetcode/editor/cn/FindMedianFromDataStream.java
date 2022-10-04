//中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。 
//
// 例如， 
//
// [2,3,4] 的中位数是 3 
//
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5 
//
// 设计一个支持以下两种操作的数据结构： 
//
// 
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。 
// double findMedian() - 返回目前所有元素的中位数。 
// 
//
// 示例： 
//
// addNum(1)
//addNum(2)
//findMedian() -> 1.5
//addNum(3) 
//findMedian() -> 2 
//
// 进阶: 
//
// 
// 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 
//
// Related Topics 设计 双指针 数据流 排序 堆（优先队列） 👍 759 👎 0

package leetcode.editor.cn;

import java.util.PriorityQueue;

/**
 * title: 295 : 数据流的中位数
 * create: 2022-10-04 15:43:33
 */
public class FindMedianFromDataStream {
    public static void main(String[] args) {
        MedianFinder solution = new FindMedianFromDataStream().new MedianFinder();
        for (int i = 1; i < 50; i++) {
            solution.addNum(i);
        }
        System.out.println(solution.findMedian());
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MedianFinder {
        // 小项堆: 堆顶为堆内最大数
        PriorityQueue<Integer> minQueue;
        // 大项堆: 堆顶为堆内最小数
        PriorityQueue<Integer> maxQueue;

        public MedianFinder() {
            minQueue = new PriorityQueue<>();
            maxQueue = new PriorityQueue<>((a, b) -> b - a);
        }

        public void addNum(int num) {
            if (maxQueue.size() >= minQueue.size()) {
                maxQueue.offer(num);
                minQueue.offer(maxQueue.poll());
            } else {
                minQueue.offer(num);
                maxQueue.offer(minQueue.poll());
            }
        }

        public double findMedian() {
            if (minQueue.size() > maxQueue.size()) {
                return minQueue.peek();
            } else if (minQueue.size() < maxQueue.size()) {
                return maxQueue.peek();
            } else {
                return (minQueue.peek() + maxQueue.peek()) / 2.0;
            }
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)

}