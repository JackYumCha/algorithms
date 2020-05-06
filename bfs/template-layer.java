void method(){
    // List type can be ArrayList, HashSet if you need to remove duplicates
    ListType<Type> layer = new ListType(); 
    
    // dijkstra's if you need to mark something has been accessed. can be boolean[] for arry boolean[][] for matrix
    HashSet<Type> visited = new HashSet(); 
    int stepCount = 0; // if you are counting the min number of steps
    
    while(!layer.isEmpty()){
        Type n = q.pollFirst();
        ListType<Type> next = new ListType();
        // check if n meets your condition, break and terminate if necessary
        for(Type n : layer){
            for(Type child : getChildren(n)){
                // check if child meet the condition
                next.add(child);
            }
        }
        layer = next;
        stepCount++;
    }
    return ???;
}
