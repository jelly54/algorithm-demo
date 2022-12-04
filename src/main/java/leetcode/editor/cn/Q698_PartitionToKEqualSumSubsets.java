//给定一个整数数组 nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。 
//
// 
//
// 示例 1： 
//
// 
//输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
//输出： True
//说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。 
//
// 示例 2: 
//
// 
//输入: nums = [1,2,3,4], k = 3
//输出: false 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= len(nums) <= 16 
// 0 < nums[i] < 10000 
// 每个元素的频率在 [1,4] 范围内 
// 
//
// Related Topics 位运算 记忆化搜索 数组 动态规划 回溯 状态压缩 👍 860 👎 0

package leetcode.editor.cn;

/**
 * title: 698 : 划分为k个相等的子集
 * create: 2022-11-17 09:58:50
 */
public class Q698_PartitionToKEqualSumSubsets {
    public static void main(String[] args) {
        Solution solution = new Q698_PartitionToKEqualSumSubsets().new Solution();
        boolean b = solution.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4);
        System.out.println(b);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canPartitionKSubsets(int[] nums, int k) {
            if (k > nums.length) return false;
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            if (sum % k != 0) return false;

            boolean[] used = new boolean[nums.length];
            int target = sum / k;
            return backtrack(k, 0, nums, 0, used, target);
        }

        /**
         * @param k      分成多少个桶
         * @param bucket 当前桶的数值
         * @param nums   数字
         * @param start  nums中开始位置
         * @param used   数字使用情况
         * @param target 目标值
         * @return 是否分完
         */
        private boolean backtrack(int k, int bucket, int[] nums, int start, boolean[] used, int target) {
            if (k == 0) {
                return true;
            }
            if (bucket == target) {
                return backtrack(k - 1, 0, nums, 0, used, target);
            }

            for (int i = start; i < nums.length; i++) {
                if (used[i]) {
                    continue;
                }
                if (nums[i] + bucket > target) {
                    continue;
                }
                // 做选择
                used[i] = true;
                bucket += nums[i];
                if (backtrack(k, bucket, nums, i + 1, used, target)) {
                    return true;
                }
                // 撤销选择
                used[i] = false;
                bucket -= nums[i];
            }

            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}