// 36ms 84.02%
class WordDictionary {

    T t = new T();
    /** Initialize your data structure here. */
    public WordDictionary() {
        
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        t.load(word, 0, word.length());
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return t.query(word, 0, word.length());
    }
}


class T{
    T[] ts = new T[26];
    boolean hit;
    void load(String v, int i, int l){
        if(i == l){
            hit = true;
        }
        else{
            int j = v.charAt(i) - 'a';
            if(ts[j] == null) ts[j] = new T();
            ts[j].load(v, i+1, l);
        }
    }
    boolean query(String v, int i, int l){
        if(i == l){
            return hit;
        }
        else{
            char c = v.charAt(i);
            if(c == '.'){
                for(int j = 0; j < 26; j++){
                    if(ts[j] == null) continue;
                    if(ts[j].query(v, i+1, l)) return true; // early termination
                }
                return false;
            }
            else{
                int j = c - 'a';
                if(ts[j] == null) return false;
                return ts[j].query(v, i+1, l);
            }
        }
    }
    
}
