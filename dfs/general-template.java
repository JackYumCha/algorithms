
// in general
returnType dfs(Node n, parameters){
  Node[] next = [get connected children or neightbours];
  
  // order of computing node and children will affect the order of traverse
  
  returnType result = [compute result for current node];
  // optional early termination
  if(condition(result)) return result;
  for(Node i : next){
    // aggregation could be sum, could be && and anything
    result = aggregate(dfs(i, child-parameters))
    // optional early termination
    if(condition(result)) return result;
  }
  return result;
}

