// 9ms 24.67%
// PriorityQueue 算法
class Solution {
    int calculate(String s) {
        N last = null, x;
        int b = 0, i = 0, l = s.length();
        PriorityQueue<N> q = new PriorityQueue(N.c);
        while(i < l){
            char c = s.charAt(i);
            x = null;
            if('0' <= c && c <= '9'){
                int n = 0;
                do{
                    n = n * 10 + (c - '0');
                    i++;
                    if(i == l) break;
                    c = s.charAt(i);
                }
                while('0' <= c && c <= '9');
                x = new N(b, n, i);
                i--;
            }
            else if(c == '('){
                b++;
            }
            else if(c == ')'){
                b--;
            }
            else if(c == ' '){

            }
            else{
                x = new N(b, c, i);
            }
            if(x != null){
                if(last != null){
                    last.n = x;
                    x.p = last;
                }
                q.add(x);
                last = x;
                if(x.i[1] == 0 && x.p != null){
                    if(x.p.p == null){
                        x.p.i[0] = Integer.MAX_VALUE;
                        x.v *= (x.p.o == '-') ? -1 : 1;
                        x.p = null;
                    }
                    else if(x.p.p.i[1] > 0){
                        x.p.i[0] = Integer.MAX_VALUE;
                        x.v *= (x.p.o == '-') ? -1 : 1;
                        x.p = x.p.p;
                        x.p.n = x;
                    }
                }
            }
            i++;
        }
        N f = null;
        while(!q.isEmpty()){
            f = q.remove();
            if(f.i[0] == Integer.MAX_VALUE) continue;
            if(q.isEmpty()) return f.v;
            f.compute(q);
        }
        return 0;
    }
}

class N{
    int v;
    char o; // operator
    int[] i = new int[3]; // {()level, */ 2 +- 1 num 0, index}
    N p, n;
    N(int b, int v, int j){
        i[0] = b;
        this.v = v;
        i[2] = j;
    }
    N(int b, char o, int j){
        i[0] = b;
        this.o = o;
        i[1] = (o == '+' || o == '-') ? 1 : 2;
        i[2] = j;
    }
    void compute(PriorityQueue<N> q){
        if(i[1] == 0){
            i[0]--; // downgrade
            q.add(this);
        }
        else{
            switch(o){
                case '+': v = p.v + n.v; break;
                case '-': v = p.v - n.v; break;
                case '*': v = p.v * n.v; break;
                case '/': v = p.v / n.v; break;
            }
            i[1] = 0; // set to number level
            N pp = p.p, nn = n.n;
            p.i[0] = Integer.MAX_VALUE;
            n.i[0] = Integer.MAX_VALUE;
            if(pp != null){
                p = pp;
                pp.n = this;
            }
            else {
                p = null;
            }
            if(nn != null){
                n = nn;
                nn.p = this;
            }
            else{
                n = null;
            }
            q.add(this);
        }
    }
    static Comparator c = new Comparator(){
        @Override
        public int compare(Object o1, Object o2){
            N n1 = (N)o1, n2 = (N)o2;
            if(n1.i[0] == n2.i[0]){
                if(n1.i[1] == n2.i[1]){
                    return Integer.compare(n1.i[2], n2.i[2]);
                }
                else{
                    return Integer.compare(n2.i[1], n1.i[1]);
                }
            }
            else{
                return Integer.compare(n2.i[0], n1.i[0]);
            }
        }
    };
}
