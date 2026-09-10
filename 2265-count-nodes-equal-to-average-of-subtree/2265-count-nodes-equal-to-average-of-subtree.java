class Solution {

    int answer = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return answer;
    }

    // returns {sum of subtree, number of nodes}
    private int[] dfs(TreeNode node) {

        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int sum = left[0] + right[0] + node.val;

        int count = left[1] + right[1] + 1;

        int average = sum / count;

        if (average == node.val) {
            answer++;
        }

        return new int[]{sum, count};
    }
}