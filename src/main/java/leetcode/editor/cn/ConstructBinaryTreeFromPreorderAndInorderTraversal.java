//给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并
//返回其根节点。 
//
// 
//
// 示例 1: 
// 
// 
//输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//输出: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//输入: preorder = [-1], inorder = [-1]
//输出: [-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder 和 inorder 均 无重复 元素 
// inorder 均出现在 preorder 
// preorder 保证 为二叉树的前序遍历序列 
// inorder 保证 为二叉树的中序遍历序列 
// 
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 1673 👎 0

package leetcode.editor.cn;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * title: 105 : 从前序与中序遍历序列构造二叉树
 * create: 2022-07-27 15:38:07
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
        TreeNode treeNode = solution.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println(treeNode);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        HashMap<Integer, Integer> inMap;

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            // 递归
            // return recursion(preorder, inorder);

            // 迭代
            return iteration(preorder, inorder);
        }

        private TreeNode iteration(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length == 0) return null;

            TreeNode root = new TreeNode(preorder[0]);
            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            stack.push(root);

            int inorderIndex = 0;
            for (int i = 1; i < preorder.length; i++) {
                int preorderVal = preorder[i];
                TreeNode peek = stack.peek();

                if (peek.val != inorder[inorderIndex]) {
                    peek.left = new TreeNode(preorderVal);
                    stack.push(peek.left);
                } else {
                    while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                        peek = stack.pop();
                        inorderIndex++;
                    }
                    peek.right = new TreeNode(preorderVal);
                    stack.push(peek.right);
                }
            }
            return root;
        }

        private TreeNode recursion(int[] preorder, int[] inorder) {
            int preLen = preorder.length;
            // 用 前序数组，构建 inorder 的 value 和 index 的映射
            inMap = new HashMap<>(preLen);
            for (int i = 0; i < preLen; i++) {
                inMap.put(inorder[i], i);
            }

            return buildTree(preorder, 0, preLen - 1, 0);
        }

        /**
         * 前序遍历：[ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
         * 中序遍历：[ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
         */
        private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int inStart) {
            if (preStart > preEnd) return null;
            Integer inIdx = inMap.get(preorder[preStart]);

            TreeNode root = new TreeNode(preorder[preStart]);
            // 得到左子树中的节点数目
            int remain = inIdx - inStart;

            // 先序遍历中「从 左边界+1 开始的 remain」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
            root.left = buildTree(preorder, preStart + 1, preStart + remain, inStart);
            // 先序遍历中「从 左边界+1 + remain 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
            root.right = buildTree(preorder, preStart + 1 + remain, preEnd, inIdx + 1);
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}