/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Case 1: both nodes are null
        if (p == null && q == null) {
            return true;
        }
        
        // Case 2: one is null, other is not
        if (p == null || q == null) {
            return false;
        }
        
        // Case 3: values are different
        if (p.val != q.val) {
            return false;
        }
        
        // Case 4: check left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}