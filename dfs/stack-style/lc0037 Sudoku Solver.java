// 10ms 55.53% 
class Solution {
    public void solveSudoku(char[][] board) {
        int[] row = new int[9], col = new int[9], sub = new int[9];
        int[][] gd = new int[9][9];
        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                char i = board[r][c];
                int v = i == '.' ? 0 : (i - '0');
                gd[r][c] = v;
                if(v == 0) continue;
                row[r] |= 1<<v;
                col[c] |= 1<<v;
                sub[(r/3)*3 + c/3] |= 1<<v;
            }
        }
        Stack<CTX> stack = new Stack();
        stack.push(new CTX(0,0));
        while(!stack.isEmpty()){
            CTX x = stack.peek();
            if(x.s == 0){
                if(x.r == 9){
                    stack.pop();
                    if(stack.isEmpty()) continue;
                    else{
                        stack.peek().v = true;
                        continue;
                    }
                }
                x.loop = gd[x.r][x.c] == 0;
                x.j = 1;
                x.s++;
            }
            if(x.s == 1){
                if(x.loop){
                    boolean shouldContinue = false;
                    while(x.j < 10){
                        if(x.ws == 0){
                            if(((row[x.r]>>x.j)&1) == 1 || ((col[x.c]>>x.j)&1) == 1 || ((sub[(x.r/3)*3 + x.c/3]>>x.j)&1) == 1) {
                                x.j++;
                                continue;
                            };
                            row[x.r] |= 1<<x.j; // v = 3  0000001 -> 0001000
                            col[x.c] |= 1<<x.j;
                            sub[(x.r/3)*3 + x.c/3] |= 1<<x.j;
                            gd[x.r][x.c] = x.j;
                            stack.push(new CTX(x.r, x.c+1));
                            x.ws = 1;
                            shouldContinue = true;
                            break;
                        }
                        else if(x.ws == 1){
                            if(x.v){
                                stack.pop();
                                if(stack.isEmpty()) {
                                    shouldContinue = true;
                                    break;
                                }
                                else{
                                    stack.peek().v = true;
                                    shouldContinue = true;
                                    break;
                                }
                            }
                            row[x.r] &= ~(1<<x.j); // 0001000 -> ~ 1110111
                            col[x.c] &= ~(1<<x.j);
                            sub[(x.r/3)*3 + x.c/3] &= ~(1<<x.j);
                            x.ws = 0;
                            x.j++;
                        }
                    }
                    if(shouldContinue) continue;
                    gd[x.r][x.c] = 0; // must undo
                    x.s = 2;
                }
                else{
                    if(x.ws == 0){
                        stack.push(new CTX(x.r, x.c+1));
                        x.ws = 1;
                        continue;
                    }
                    else if(x.ws == 1){
                        if(x.v){
                            stack.pop();
                            if(stack.isEmpty()) continue;
                            else{
                                stack.peek().v = true;
                                continue;
                            }
                        }
                    }
                }
                x.s = 2;
            }
            if(x.s == 2){
                x.s = 0;
                stack.pop();
                if(stack.isEmpty()) continue;
                else{
                    stack.peek().v = false;
                    continue;
                }
            }
        }
        // after dfs
        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                board[r][c] = (char)('0' + gd[r][c]);
            }
        }
    }
}


class CTX{
    int r, c, s, j, ws;
    boolean loop, v; // result from next level;
    CTX(int r, int c){
        if(c == 9){
            c = 0;
            r++;
        }
        this.r = r;
        this.c = c;
    }
}
