// 3ms 91.40%
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] incomingEdgeCount = new int[numCourses];
        ArrayList<Integer>[] nextCourses = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++) nextCourses[i] = new ArrayList();
        for(int[] prerequisite : prerequisites){
            int from = prerequisite[0], to = prerequisite[1];
            incomingEdgeCount[to]++;
            nextCourses[from].add(to);
        }
        LinkedList<Integer> q = new LinkedList();
        int count = 0;
        for(int i = 0; i < numCourses; i++) {
            if(incomingEdgeCount[i] > 0) continue;
            q.add(i);
            count++;
        }
        while(!q.isEmpty()){
            int course = q.pollFirst();
            for(int nextCourse : nextCourses[course]){
                incomingEdgeCount[nextCourse]--;
                if(incomingEdgeCount[nextCourse] > 0) continue;
                q.add(nextCourse);
                count++;
            }
        }
        return count == numCourses;
    }
}
