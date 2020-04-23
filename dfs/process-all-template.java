// in general
aggregatedType overall_aggregation_value;

returnType dfs(Node n, parameters){
  Node[] next = [get connected children or neightbours];
  returnType result = [compute result for current node];
  // optional early termination
  
  // order of do_something on node and children will affect the order of traverse
  
  // optional depend on traverse order
  do_something(n);
  
  // optional aggregate global results
  aggregate_overall(result, overall_aggregation_value);
  
  if(condition(result)) return result;
  for(Node i : next){
    // aggregation could be sum, could be && and anything
    result = aggregate(dfs(i, child-parameters))
    // optional early termination
    if(condition(result)) return result;
  }
  // optional depend on traverse order
  do_something(n);
  
  // optional aggregate global results
  aggregate_overall(result, overall_aggregation_value);
  
  return result;
}

