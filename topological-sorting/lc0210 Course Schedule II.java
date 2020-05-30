// 3ms 94.31%
class Solution {
    static int[] empty = new int[0];
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] incomingCount = new int[numCourses];
        ArrayList<Integer>[] nextCourses = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++) nextCourses[i] = new ArrayList();
        for(int[] prerequisite : prerequisites){
            int from = prerequisite[1], to = prerequisite[0];
            incomingCount[to]++;
            nextCourses[from].add(to);
        }
        
        LinkedList<Integer> q = new LinkedList();
        int count = 0;
        for(int i = 0; i < numCourses; i++) {
            if(incomingCount[i] == 0){
                q.add(i);
            }
        }
        
        int[] result = new int[numCourses];
        while(!q.isEmpty()){
            int course = q.pollFirst();
            result[count] = course;
            count++;
            for(int next : nextCourses[course]){
                incomingCount[next]--;
                if(incomingCount[next] == 0){
                    q.add(next);
                }
            }
        }
        
        return count == numCourses ? result : empty;
    }
}
