// 27ms 100%
class Trie {

    Trie[] ts = new Trie[26];
    boolean hit = false;
    
    /** Initialize your data structure here. */
    public Trie() {
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        load(word, 0, word.length());
    }
    
    void load(String v, int i, int l){
        if(i == l){
            hit = true;        
        }   
        else{
            int j = v.charAt(i) - 'a';
            if(ts[j] == null) ts[j] = new Trie();
            ts[j].load(v, i+1, l);
        }
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return query(word, 0, word.length());
    }
    
    boolean query(String v, int i, int l){
        if(i == l){
            return hit;
        }
        else{
            int j = v.charAt(i) - 'a';
            if(ts[j] == null) return false;
            return ts[j].query(v, i+1, l);
        }
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return queryPrefix(prefix, 0, prefix.length());
    }
    
    boolean queryPrefix(String v, int i, int l){
        if(i == l){
            if(hit) return true;
            for(int j = 0; j < 26; j++){
                if(ts[j] != null) return true;
            }
            return false;
        }
        else{
            int j = v.charAt(i) - 'a';
            if(ts[j] == null) return false;
            return ts[j].queryPrefix(v, i+1, l);
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

