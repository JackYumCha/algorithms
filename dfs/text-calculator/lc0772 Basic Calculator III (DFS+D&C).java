// 1ms 100%
public class Solution {
    String s;
    int i, l;
    public int calculate(String s) {
        this.s = s;
        i = 0;
        l = s.length();
        return dfs1();
    }
    int dfs1(){ // compute an expression without () compute add/deduct only
        int sum = 0, sign = 1;
        while(i < l){
            char c = s.charAt(i);
            if(c == '(' || (c >= '0' && c <= '9') ){
                int n = dfs2();
                sum += sign * n;
                sign = 1;
            }
            else if(c == ')'){
                return sum;
            }
            else if(c == '-'){
                sign *= -1;
            }
            i++;
        }
        return sum;
    }
    int dfs2(){ // read a multiply/divide group
        int p = 1;
        boolean mul = true;
        while(i < l){
            char c = s.charAt(i);
            if(c >= '0' && c <= '9'){
                int n = dfs3();
                if(mul) p *= n;
                else p /= n;
            }
            else if(c == '*'){
                mul = true;
            }
            else if(c == '/'){
                mul = false;
            }
            else if(c == '('){
                i++;
                int n = dfs1();
                if(mul) p *= n;
                else p /= n;
            }
            else if(c == ')' || c == '+' || c == '-'){
                i--;
                return p;
            }
            i++;
        }
        i--;
        return p;
    }
    int dfs3(){ // read a number
        int n = 0;
        while(i < l && s.charAt(i) >= '0' && s.charAt(i) <= '9'){
            n = n * 10 + (s.charAt(i) - '0');
            i++;
        }
        i--;
        return n;
    }
}
