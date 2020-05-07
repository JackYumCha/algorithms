// 19ms 8.20%
class Solution {
    int[][] mt;
    int cr, cc;
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        mt = maze;
        cr = maze.length;
        cc = maze[0].length;
    
        boolean[][] visited = new boolean[cr][cc];
        
        LinkedList<int[]> q = new LinkedList();
        q.offerLast(start);
        while(!q.isEmpty()){
            int[] p = q.pollFirst();
            if(p[0] == destination[0] && p[1] == destination[1]) return true;
            visited[p[0]][p[1]] = true;
            int[] u = move(p[0], p[1], -1, 0),
                d = move(p[0], p[1], 1, 0),
                l = move(p[0], p[1], 0, -1),
                r = move(p[0], p[1], 0, 1);
            if(!visited[u[0]][u[1]]) q.offerLast(u);
            if(!visited[d[0]][d[1]]) q.offerLast(d);
            if(!visited[l[0]][l[1]]) q.offerLast(l);
            if(!visited[r[0]][r[1]]) q.offerLast(r);
        }
        return false;
    }
    
    int[] move(int r, int c, int dr, int dc){
        // not need to check (r,c)
        int nr = r + dr, nc = c + dc;
        while(nr >= 0 && nr < cr && nc >= 0 && nc < cc && mt[nr][nc] == 0){
            r = nr;
            c = nc;
            nr = r + dr;
            nc = c + dc;
        }
        return new int[]{r, c};
    }
}
