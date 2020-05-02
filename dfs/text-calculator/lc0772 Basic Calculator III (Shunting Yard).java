// 2ms 63.81%
class Solution{
    int calculate(String s){
        int b = 0, sign = 1;
        LinkedList<Integer> ns = new LinkedList(), os = new LinkedList(), vs = new LinkedList(), as = new LinkedList();
        boolean expectNum = true;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            switch (c){
                case '(': b++; break;
                case ')': b--; break;
                case '+': {
                    if(expectNum){
                        sign = 1;
                    }
                    else{
                        os.offerLast(0 | (b << 2));
                        expectNum = true;
                    }
                }break;
                case '-':{
                    if(expectNum){
                        sign = -1;
                    }
                    else{
                        os.offerLast(1 | (b << 2));
                        expectNum = true;
                    }
                }break;
                case '*':{
                    os.offerLast(2 | (b << 2));
                    expectNum = true;

                }break;
                case '/':{
                    os.offerLast(3 | (b << 2));
                    expectNum = true;
                }break;
                case ' ': break;
                default: {
                    int n = c - '0';
                    while(i+1 < s.length() && '0' <= s.charAt(i+1) && s.charAt(i+1) <= '9'){
                        i++;
                        c = s.charAt(i);
                        n = n * 10 + (c - '0');
                    }
                    ns.offerLast(n * sign);
                    System.out.printf("%1$d\n", n * sign);
                    sign = 1;
                    expectNum = false;
                }break;
            }
        }
        while(!ns.isEmpty()){
            int n = ns.pollFirst();
            if(os.isEmpty()){
                while(!as.isEmpty()){
                    n = compute(vs.pollLast(), n, as.pollLast());
                    System.out.printf("%1$d\n", n);
                }
                return n;
            }
            else{
                int o = os.pollFirst();
                while(!as.isEmpty() && (as.peekLast()>>1) >= (o >> 1)){
                    n = compute(vs.pollLast(), n, as.pollLast());
                    System.out.printf("%1$d\n", n);
                }
                vs.offerLast(n);
                as.offerLast(o);
            }
        }
        return 0;
    }
    int compute(int v1, int v2, int op){
        System.out.printf("%1$d %2$s %3$d = ", v1, new char[]{'+','-','*','/'}[op & 3], v2);
        switch (op & 3) {
            case 0: return v1 + v2;
            case 1: return v1 - v2;
            case 2: return v1 * v2;
            case 3: return v1 / v2;
        }
        return 0;
    }
}
