// 12ms 98.88%
class Solution {
    public int openLock(String[] deadends, String target) {
        int t = toInt(target);
        boolean[] visited = new boolean[10000];
        for(String d: deadends){
            visited[toInt(d)] = true;
        }
        LinkedList<int[]> q = new LinkedList();
        if(visited[0]) return -1;
        q.offerLast(new int[]{0, 0});
        visited[0] = true;
        while(!q.isEmpty()){
            int[] c = q.pollFirst();
            if(c[0] == t) return c[1];
            for(int n : next(c[0])){
                if(visited[n]) continue;
                q.offerLast(new int[]{n, c[1] + 1});
                visited[n] = true;
            }
        }
        return -1;
    }
    int toInt(String s){
        int n = 0;
        for(int i = 0; i < 4; i++){
            n = n * 10 + (s.charAt(i) - '0');
        }
        return n;
    }
    int[] next(int k){
        int a = k / 1000, b = (k % 1000)/100, c = (k % 100) / 10, d = k % 10;
        int[] r = new int[8];
        int j = 0;
        for(int t : nextDigit(a)){
            r[j] = t * 1000 + b * 100 + c * 10 + d;
            j++;
        }
        for(int t : nextDigit(b)){
            r[j] = a * 1000 + t * 100 + c * 10 + d;
            j++;
        }
        for(int t : nextDigit(c)){
            r[j] = a * 1000 + b * 100 + t * 10 + d;
            j++;
        }
        for(int t : nextDigit(d)){
            r[j] = a * 1000 + b * 100 + c * 10 + t;
            j++;
        }
        return r;
    }
    int[] nextDigit(int i){
        if(i == 0){
            return new int[]{9, 1};
        }
        else if(i == 9){
            return new int[]{8, 0};
        }
        else{
            return new int[]{i-1, i+1};
        }
    }
}
