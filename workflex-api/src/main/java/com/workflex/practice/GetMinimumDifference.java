package com.workflex.practice;

public class GetMinimumDifference {
    int min = Integer.MAX_VALUE;
    TreeNode prev = null;

    public int getMinimumDifference(TreeNode root) {
        if(root == null) return 0;
        if (root.left == null && root.right == null) return 0;

        recFn(root);
        return min;
    }

    private void recFn(TreeNode node){
        if (node == null) return;

        recFn(node.left);

        if(prev != null){
            min = Math.min(min, node.val - prev.val);
        }

        prev = node;

        recFn(node.right);
    }
}
