class Solution {
    int diameter = 0;  // global variable - max diameter track karega

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return diameter;
    }

    private int depth(TreeNode node) {
        if (node == null) return 0;

        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);

        // is node se guzarne wala path (edges count)
        diameter = Math.max(diameter, leftDepth + rightDepth);

        // upar wale node ko height chahiye, isliye 1+max return
        return 1 + Math.max(leftDepth, rightDepth);
    }
}