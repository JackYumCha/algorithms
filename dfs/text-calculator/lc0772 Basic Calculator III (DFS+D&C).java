// 1ms 100%
class Solution{
    String s;
    int i, l;
    public int calculate(String s) {
        this.s = s;
        i = 0;
        l = s.length();
        return dfs1();
    }
    int dfs1(){ // () and +-
        int sum = 0, sign = 1; 
        while(i < l){
            char c = s.charAt(i);
            if((c>='0' && c<='9') || c == '(' ){
                sum += sign * dfs2();
                sign = 1;
            }
            else if(c == '-'){
                sign = -sign;
            }
            else if(c==')'){
                return sum;
            }
            i++;
        }
        return sum;
    }
    int dfs2(){ //  
        int product = 1; // product
        boolean isMulti = true; // is multiply(true) or divide(false)
        while(i<l){
            char c = s.charAt(i);
            if(c>='0' && c<='9'){
                int n = dfs3();
                if(isMulti) product *= n;
                else product /= n;
            }
            else if(c == '*'){
                isMulti = true;
            }
            else if(c == '/'){
                isMulti = false;
            }
            else if(c == '('){
                i++;
                int n = dfs1();
                if(isMulti) product *= n;
                else product /= n;
            }
            else if(c==')' || c=='+' || c=='-'){
                i--;
                return product;
            }
            i++;
        }
        return product;
    }
    int dfs3(){ // read a num
        int n = 0;
        while(i<l && s.charAt(i) >= '0' && s.charAt(i) <= '9'){
            n = n * 10 + (s.charAt(i) - '0');
            i++;
        }
        i--;
        return n;
    }
}
