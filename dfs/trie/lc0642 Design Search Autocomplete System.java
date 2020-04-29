// 109ms 92.80%
class AutocompleteSystem {
    HashMap<String, Integer> hm = new HashMap();
    T t = new T();
    StringBuilder stb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        stb = new StringBuilder();
        int n = times.length;
        for(int j = 0; j < n; j++){
            add(sentences[j], times[j]);
        }
    }
    static ArrayList<String> empty = new ArrayList();
    public List<String> input(char c) {
        if(c == '#'){
            add(stb.toString(), 1);
            stb = new StringBuilder();
            return empty;
        }
        else{
            stb.append(c);
            String s = stb.toString();
            return t.query(s, 0, s.length());
        }
    }
    
    void add(String s, int f){
        hm.put(s, hm.getOrDefault(s, 0) + f);
        t.load(s, 0, s.length(), hm);
    }
}

class T{
    T[] ts = new T[27]; // include ' '
    ArrayList<String> arr = new ArrayList();
    void load(String s, int i, int l, HashMap<String, Integer> hm){
        PriorityQueue<C> q = new PriorityQueue(C.c);
        boolean nf = true;
        for(String a: arr){
            if(a.equals(s)) nf = false;
            q.add(new C(a, hm.get(a)));
        }
        if(nf) q.add(new C(s, hm.get(s)));
        arr.clear();
        while(!q.isEmpty() && arr.size() < 3){
            arr.add(q.remove().s);
        }
        if(i < l){
            int j = index(s.charAt(i));
            if(ts[j] == null) ts[j] = new T();
            ts[j].load(s, i + 1, l, hm); 
        }
    }
    static ArrayList<String> empty = new ArrayList();
    ArrayList<String> query(String s, int i, int l){
        if(i == l){
            return arr;
        }
        else{
            int j = index(s.charAt(i));
            if(ts[j] == null) return empty;
            else return ts[j].query(s, i + 1, l);
        }
    }
    static int index(char c){
        if(c == ' ') return 0;
        else return 1 + (c - 'a');
    }
}

class C{
    String s;
    int t;
    C(String s, int t){
        this.s = s;
        this.t = t;
    }
    static Comparator c = new Comparator(){
        @Override
        public int compare(Object o1, Object o2){
            C c1 = (C)o1, c2 = (C)o2;
            if(c1.t == c2.t) return c1.s.compareTo(c2.s);
            else return Integer.compare(c2.t, c1.t);
        }
    };
}

// 161ms 48.39% 
class AutocompleteSystem {
    T t = new T();
    StringBuilder stb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        int l = times.length;
        for(int i = 0; i < l; i++){
            t.load(sentences[i], times[i], 0, sentences[i].length());
        }
        stb = new StringBuilder();
    }
    
    public List<String> input(char c) {
        if(c == '#'){
            String s = stb.toString();
            t.load(s, 1, 0, s.length());
            stb = new StringBuilder();
            return new ArrayList();
        }
        else{
            stb.append(c);
            TreeSet<SC> ts = new TreeSet(T.cpt);
            String s = stb.toString();
            t.query(s, 0, s.length(), ts);
            ArrayList<String> arr = new ArrayList();
            for(SC v: ts) arr.add(v.s);
            return arr;
        }
    }
}

class T{
    static Comparator cpt = new Comparator(){
        @Override
        public int compare(Object o1, Object o2){
            SC s1 = (SC)o1, s2 = (SC)o2;
            if(s1.c == s2.c) return s1.s.compareTo(s2.s);
            else return Integer.compare(s2.c, s1.c);
        }
    };
    T[] cs = new T[27];
    TreeSet<SC> ts = new TreeSet(cpt);
    HashMap<String, SC> hm = new HashMap();
    void load(String s, int t, int i, int l){
        if(i == l){
            if(hm.containsKey(s)){
                SC sc = hm.get(s);
                ts.remove(sc);
                sc.c += t;
                ts.add(sc);
            }
            else{
                SC sc = new SC(s, t);
                ts.add(sc);
                hm.put(s, sc);
            }
        }
        else{
            char c = s.charAt(i);
            int j;
            if(c == ' '){
                j = 0;
            }
            else{
                j = 1 + (c - 'a');
            }
            if(cs[j] == null) cs[j] = new T();
            cs[j].load(s, t, i+1, l);
        }
    }
    void query(String s, int i, int l, TreeSet<SC> top){
        if(i == l){
            collect(top);
        }
        else{
            int c = s.charAt(i);
            int j;
            if(c == ' ') j = 0;
            else {
                j = 1 + (c - 'a');
            }
            if(cs[j] == null) return;
            else {
                cs[j].query(s, i+1, l, top);
            }
        }
    }
    // collect data from the children
    void collect(TreeSet<SC> top){
        for(SC sc : ts){
            if(top.size() < 3){
                top.add(sc);
            }
            else{
                if(cpt.compare(sc, top.last()) < 0){
                    top.remove(top.last());
                    top.add(sc);
                }
                else {
                    break; // when sc is smaller than last, break here 
                }
            }
        }
        for(T t: cs){
            if(t != null) t.collect(top);
        }
    }
}
class SC{
    String s; // sentence
    int c; // count
    SC(String s, int c){
        this.s = s;
        this.c = c;
    }
}
