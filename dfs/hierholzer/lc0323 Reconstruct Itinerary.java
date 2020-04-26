// 4ms 100%
class Solution {
    HashMap<String, PriorityQueue<String>> map;
    public List<String> findItinerary(List<List<String>> tickets) { // heirholzer ?? greedy
        map = new HashMap();
        for(List<String> ticket : tickets){
            String from = ticket.get(0), to = ticket.get(1);
            if(!map.containsKey(from)) map.put(from, new PriorityQueue());
            map.get(from).add(to);
        }
        LL ll = new LL("JFK");
        dfs(ll);
        ArrayList<String> al = new ArrayList();
        N h = ll.h;
        while(h != null){
            al.add(h.v);
            h = h.n;
        }
        return al;
    }
    
    private void dfs(LL ll){ // heirholzer
        String from = ll.t.v;
        PriorityQueue<String> q = map.getOrDefault(from, null);
        if(q == null || q.isEmpty()) return; // no further
        LL end = new LL(q.remove());
        dfs(end);
        while(!q.isEmpty()){ // internal loops
            N n = new N(q.remove());
            ll.t.n =n;
            ll.t = n;
            dfs(ll); // append loop
        }
        ll.t.n = end.h;
        ll.t = end.t;
    }
}

class N{
    N n;
    String v;
    N(String v){
        this.v = v;
    }
}
class LL{
    N h, t;
    LL(String v){
        N n = new N(v);
        h = n;
        t = n;
    }
}
