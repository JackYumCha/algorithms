// 2ms 63.81%
class Solution{
    // op = + 0 - 1 * 2 / 3  | level << 2
    public int calculate(String s) {
        LinkedList<Integer> values = new LinkedList(), operators = new LinkedList(), vstack = new LinkedList(), opstack = new LinkedList();
        int l = s.length(), sign = 1, level = 0;
        boolean expectNum = true;
        for(int i = 0; i < l; i++){
            char c = s.charAt(i);
            switch(c){
                case '+':{
                    if(expectNum){
                        sign = 1;
                    }
                    else{
                        operators.offerLast(level<<2);
                        expectNum = true;
                    }
                }break;
                case '-':{
                    if(expectNum){
                        sign = -1;
                    }
                    else{
                        operators.offerLast((level<<2)|1);
                        expectNum = true;
                    }
                }break;
                case '*':{
                    operators.offerLast((level<<2)|2);
                    expectNum = true;
                }break;
                case '/':{
                    operators.offerLast((level<<2)|3);
                    expectNum = true;
                }break;
                case '(': level++; break;
                case ')': level--; break;
                case ' ': break;
                default:{ // 0-9
                    int n = (c - '0');
                    while(i+1<l && s.charAt(i+1) >= '0' && s.charAt(i+1) <= '9'){
                        i++;
                        n = n * 10 + (s.charAt(i) - '0');
                    }
                    values.offerLast(sign * n);
                    sign = 1;
                    expectNum = false;
                };
            }
        }
        while(!values.isEmpty()){
            int n = values.pollFirst();
            if(operators.isEmpty()){
                while(!vstack.isEmpty()){
                    n = compute(vstack.pollLast(), n, opstack.pollLast());
                }
                return n; // always return from here
            }
            else{
                int op = operators.pollFirst();
                while(!opstack.isEmpty() && (opstack.peekLast()>>1) >= (op >> 1)){
                    n = compute(vstack.pollLast(), n, opstack.pollLast());
                }
                vstack.offerLast(n);
                opstack.offerLast(op);
            }            
        }
        return 0;
    }
    int compute(int v1, int v2, int op){
        switch(op & 3){
            case 0: return v1 + v2;
            case 1: return v1 - v2;
            case 2: return v1 * v2;
            case 3: return v1 / v2;
        }
        return 0;
    }
}
