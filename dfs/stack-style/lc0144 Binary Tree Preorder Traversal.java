class Solution {
    ArrayList<Integer> arr;
    public List<Integer> preorderTraversal(TreeNode root) {
        arr = new ArrayList();
        CTX c;
        Stack<CTX> ll = new Stack();
        ll.push(new CTX(root));
        while(!ll.isEmpty()){
            c = ll.peek();
            switch(c.s){
                case 0:{
                    if(c.n == null){
                        ll.pop();
                        continue;
                    }
                    else{
                        arr.add(c.n.val);
                        c.s++;
                        ll.push(new CTX(c.n.left));
                        continue;
                    }
                }
                case 1:{
                    c.s++;
                    ll.push(new CTX(c.n.right));
                    continue;
                }
                case 2: {
                    // end
                    ll.pop();
                }
            }
        }
        return arr;
    }
}

class CTX{
    TreeNode n; // stage
    int s; // stage
    CTX(TreeNode n){
        this.n = n;
    }
}
