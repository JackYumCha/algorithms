void method(){
    LinkedList<Type> q = new LinkedList();
    while(!q.isEmpty()){
      Type n = q.pollFirst();
      // check if n meets your condition, break and terminate if necessary
      for(Type child : getChildren(n)){
          // check if child meet the condition
          q.offerLast(child);
      }
    }
}
