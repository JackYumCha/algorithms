// hold the matrix
int[][] gd;
// keep the row count, column count;
int cr, cc;
// optional visited to maintain if only need to traverse once
boolean[][] visited;
entryMethod(int[][] matrix, other parameters){
  gd = matrix;
  cr = gd.length;
  if(cr == 0) return ?;
  cc = gd[0].length;
  if(cc == 0) return ?;
  visited = new boolean[cr][cc];
  for(int r = 0; r < cr; r++){
    for(int c = 0; c < cc; c++){
      result = aggregate(dfs(r, c));
      // optional early termination
      if(condition(result)) return result; 
    }
  }
  return result;
}

dfs(int r, int c){
  if(r<0 || r>=cr || c<0 || c>=cc || (optional) visited[r][c] || condition(gd[r][c]) ) return ?;
  // do your logic here
  result = do_something(gd[r][c]);
  // optional set gd[r][c] to some value to avoid visit more than once
  gd[r][c] = ?;
  
  // call neighbours or aggregate values
  dfs(r,c+1);
  dfs(r,c-1);
  dfs(r+1,c);
  dfs(r-1,c);
}
