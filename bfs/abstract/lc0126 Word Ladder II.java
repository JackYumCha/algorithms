// 2046ms 5.02% a better version can be found in the other file
class Solution {
    int l, n; // word length
    boolean[][] cn;
    String[] wd;
    Integer[] visited;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        l = beginWord.length();
        n = wordList.size();
        boolean f = false;
        int firstIndex = n, endIndex = -1;
        for(int i = 0; i < n; i++){
            if(wordList.get(i).equals(endWord)){
                endIndex = i;
                break;
            }
        }
        for(int i = 0; i < n; i++){
            if(wordList.get(i).equals(beginWord)){
                f = true;
                firstIndex = i;
                break;
            }
        }
        if(f){
            wd = new String[n];
            for(int i = 0; i < n; i++)wd[i] = wordList.get(i);
        }
        else{
            n++;
            wd = new String[n];
            for(int i = 0; i < n - 1; i++)wd[i] = wordList.get(i);
            wd[n-1] = beginWord;
        }
        visited = new Integer[n];
        cn = new boolean[n][n];
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                cn[i][j] = replaceOne(wd[i], wd[j]);
                cn[j][i] = cn[i][j];
            }
        }
        // int[2] {index in wd, step}, index in previous steps;
        LinkedList<V> q = new LinkedList();
        q.offerLast(new V(new ArrayList(), firstIndex, 0));

        
        Integer endStep = null; //
        ArrayList<List<String>> results = new ArrayList();
        
        while(!q.isEmpty()){
            V v = q.pollFirst();
            visited[v.i] = v.s;
            if(endStep != null && v.s > endStep) break; // can be break;
            int s = v.s + 1;
            boolean[] conn = cn[v.i];
            if(v.i == endIndex){
                ArrayList<String> arr = new ArrayList();
                for(int j : v.path){
                    arr.add(wd[j]);
                }
                results.add(arr);
                if(endStep == null) endStep = v.s;
                continue;
            }
            for(int j = 0; j < n; j++){
                if(conn[j]){
                    if(visited[j] != null && visited[j] < s) continue;
                    q.offerLast(new V(v.path, j, s));
                }
            }
        }
        return results;
    }
    boolean replaceOne(String a, String b){
        int c = 0;
        for(int i = 0; i < l; i++){
            char ca = a.charAt(i), cb = b.charAt(i);
            c += ca == cb ? 0 : 1;
            if(c > 1) return false;
        }
        return c == 1;
    }
}

class V{
    int i, s;
    ArrayList<Integer> path;
    V(ArrayList<Integer> parent, int i, int s){
        this.i = i;
        this.s = s;
        path = new ArrayList();
        for(int k: parent) path.add(k);
        path.add(i);
    }
}
