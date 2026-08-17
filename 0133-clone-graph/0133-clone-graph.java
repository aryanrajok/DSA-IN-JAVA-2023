
class Solution {
    // original node -> cloned node mapping, cycle handle karne ke liye
    private Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // Agar iska clone pehle hi ban chuka hai, wahi return kar do
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Naya clone banao (neighbors abhi empty)
        Node clone = new Node(node.val);

        // IMPORTANT: neighbors process karne se PEHLE map mein daalo
        // warna cycle mein infinite recursion ho jayega
        visited.put(node, clone);

        // Ab har original neighbor ke liye recursively clone banao
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }

        return clone;
    }
}