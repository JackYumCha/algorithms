
// in general
returnType dfs(Node n, parameters){
  Node[] next = [get connected children or neightbours];
  returnType result = [compute result for current node];
  for(Node i : next){
    // aggregation could be sum, could be && and anything
    result = aggregate(dfs(i, child-parameters))
  }
  return result;
}

