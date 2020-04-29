class Solution{
    void main(){
        T t = new T();
        for(String s: values){
            t.load(s, 0, s.length(), other parameters);
        }
    }
    
    void query(String value){
        // when there is need to query 
        t.query(value, 0, value.length(), other parameters, result collectors);
    }
}


class T{
    T[] ts = new T[26];
    boolean hit;
    List<HitType> hits;
    void load(String v, int i, int l, other parameters...){
        if(i == l){
            hit = true;
            hits.add(v or other relevant values);
        }
        else{
            int j = v.charAt(i) - 'a';
            if(ts[j] == null) ts[j] = new T();
            ts[j].load(v, i+1, l, other parameters...);
        }
    }
    void query(String v, int i, int l, other parameters..., result collectors...){
        if(i == l){
            // result collectors should collect results here
        }
        else{
            int j = v.charAt(i) - 'a';
            if(ts[j] == null) return;
            ts[j].query(v, i+1, l, other parameters..., result collectors...);
        }
    }
}
