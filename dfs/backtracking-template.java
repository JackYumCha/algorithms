// parameters will change 
parameterType parameters;

returnType dfs(Node n, parameters){
  Node[] next = [get connected children or neightbours];
  
  // order of computing node and children will affect the order of traverse
  
  returnType result = [compute result for current node];
  // optional early termination
  if(condition(result)) return result;
  for(Node i : next){
    // do update for the parameters
    doUpdate(parameters);
  
    // aggregation could be sum, could be && and anything
    result = aggregate(dfs(i, child-parameters))
    
    // optional early termination
    if(condition(result)) return result;
    
    // undo update for the parameters
    undoUpdate(parameters);
  }
  return result;
}
