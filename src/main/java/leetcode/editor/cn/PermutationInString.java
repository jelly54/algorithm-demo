//给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。 
//
// 换句话说，s1 的排列之一是 s2 的 子串 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s1 = "ab" s2 = "eidbaooo"
//输出：true
//解释：s2 包含 s1 的排列之一 ("ba").
// 
//
// 示例 2： 
//
// 
//输入：s1= "ab" s2 = "eidboaoo"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s1.length, s2.length <= 10⁴ 
// s1 和 s2 仅包含小写字母 
// 
//
// Related Topics 哈希表 双指针 字符串 滑动窗口 👍 747 👎 0

package leetcode.editor.cn;

import java.util.HashMap;

/**
 * title: 567 : 字符串的排列
 * create: 2022-08-26 17:11:21
 */
public class PermutationInString {
    public static void main(String[] args) {
        Solution solution = new PermutationInString().new Solution();
        System.out.println(solution.checkInclusion("abcdxabcde", "abcdeabcdx"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean checkInclusion(String s1, String s2) {
            if (s1.length() > s2.length()) return false;

            HashMap<Character, Integer> needsMap = new HashMap<>();
            HashMap<Character, Integer> windowMap = new HashMap<>();
            for (char c : s1.toCharArray()) {
                needsMap.put(c, needsMap.getOrDefault(c, 0) + 1);
            }

            int left = 0, right = 0, valid = 0;
            while (right < s2.length()) {
                char c = s2.charAt(right++);
                if (needsMap.containsKey(c)) {
                    windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
                    if (windowMap.get(c).equals(needsMap.get(c))) {
                        valid++;
                    }
                }

                while (right - left >= s1.length()) {
                    // 缩小窗口时判断是否找到了，needMap 的key的数量
                    if (valid == needsMap.size()) {
                        return true;
                    }
                    char c1 = s2.charAt(left++);
                    if (needsMap.containsKey(c1)) {
                        if (windowMap.get(c1).equals(needsMap.get(c1))) {
                            valid--;
                        }
                        windowMap.put(c1, windowMap.get(c1) - 1);
                    }
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}