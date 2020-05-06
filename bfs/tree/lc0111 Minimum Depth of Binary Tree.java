//0ms 100%
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        LinkedList<Node> ll = new LinkedList();
        ll.offerLast(new Node(root, 1));
        while(!ll.isEmpty()){
            Node n = ll.pollFirst();
            if(n.n.left == null && n.n.right == null) return n.level;
            if(n.n.left != null) ll.add(new Node(n.n.left, n.level + 1));
            if(n.n.right != null) ll.add(new Node(n.n.right, n.level + 1));
        }
        return 0;
    }
}
class Node{
    TreeNode n;
    int level;
    Node(TreeNode n, int level){
        this.n = n;
        this.level = level;
    }
}
