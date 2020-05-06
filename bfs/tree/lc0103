// 1ms 71.53%
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<List<Integer>> results = new ArrayList();
        if(root == null) return results;
        LinkedList<TreeNode> layer = new LinkedList();
        layer.offerLast(root);
        boolean odd = true;
        while(!layer.isEmpty()){
            LinkedList next = new LinkedList();
            ArrayList<Integer> arr = new ArrayList();
            while(!layer.isEmpty()){
                if(odd){
                    TreeNode n = layer.pollFirst();
                    if(n.left != null) next.offerLast(n.left);
                    if(n.right != null) next.offerLast(n.right);
                    arr.add(n.val);
                }
                else{
                    TreeNode n = layer.pollLast();
                    if(n.right != null) next.offerFirst(n.right);
                    if(n.left != null) next.offerFirst(n.left);
                    arr.add(n.val);
                }
            }
            odd = !odd;
            results.add(arr);
            layer = next;
        }
        return results;
    }
}
