class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        Map<String, Integer> count = new HashMap<>();
        postorder(root, count, result);
        return result;
    }

    private String postorder(TreeNode node, Map<String, Integer> count, List<TreeNode> result) {
        if (node == null) return "#";

        String left = postorder(node.left, count, result);
        String right = postorder(node.right, count, result);
        
        String serial = node.val + "," + left + "," + right;

        count.put(serial, count.getOrDefault(serial, 0) + 1);
        
        if (count.get(serial) == 2) {
            result.add(node);
        }

        return serial;
    }
}