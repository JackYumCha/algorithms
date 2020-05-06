void method(){
    LinkedList<Type> q = new LinkedList(); // could be WrapType if needed
    
    // dijkstra's if you need to mark something has been accessed. can be boolean[] for arry boolean[][] for matrix
    HashSet<Type> visited = new HashSet(); 
    
    q.offerLast(root);
    
    while(!q.isEmpty()){
      Type n = q.pollFirst();
      // check if n meets your condition, break and terminate if necessary
      for(Type child : getChildren(n)){
          // check if child meet the condition
          q.offerLast(child);
      }
    }
}

class WrapType{
    Type item;
    int step; // if you need to count steps or find min cost etc.
}
