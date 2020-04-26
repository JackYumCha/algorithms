// 2ms 96.84%
class Solution {
    boolean[][] vs;
    int k, m;
    public String crackSafe(int n, int k) {
        StringBuilder stb = new StringBuilder();
        if(n == 1){
            for(int i = 0; i < k; i++){
                stb.append((char)('0' + i));
            }
        }
        else if(k == 1){
            for(int i = 0; i < n; i++){
                stb.append('0');
            }
        }
        else{
            m = 1;
            this.k = k;
            for(int i = 0; i < n - 1; i++) m *= k;
            vs = new boolean[m][k];
            LL ll = new LL(0);
            vs[0][0] = true;
            dfs(ll);
            N h = ll.h;
            for(int i = 1; i < n; i++){
                stb.append('0');
            }
            while(h != null){
                stb.append((char)('0'+ (h.v % k)));
                h = h.n;
            }
        }
        return stb.toString();
    }
    void dfs(LL ll){
        int from = ll.t.v % m;
        boolean[] used = vs[from];
        LL end = null;
        for(int i = 0; i < k; i++){
            if(used[i]) continue;
            used[i] = true;
            int next = from * k + i;
            if(end == null){
                end = new LL(next);
                dfs(end);
            }
            else{
                N n = new N(next);
                ll.t.n = n;
                ll.t = n;
                dfs(ll);
            }
        }
        if(end != null){
            ll.t.n = end.h;
            ll.t = end.t;
        }
    }
}

class N{
    N n;
    int v;
    N(int v){
        this.v = v;
    }
}

class LL{
    N h, t;
    LL(int v){
        N x = new N(v);
        h = x;
        t = x;
    }
}
