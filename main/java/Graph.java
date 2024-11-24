
import java.util.*;
public class Graph {
     HashMap<Integer,Node> nodes;
     ArrayList<Edge> edges;

     public Graph() {
         this.nodes = new HashMap<>();
         this.edges = new ArrayList<>();
     }
     public Graph(HashMap<Integer,Node> nodes, ArrayList<Edge> edges) {
         this.nodes = nodes;
         this.edges = edges;
     }

    public static class  Node{
        public int value;
        public int in;
        public int out;
        public ArrayList<Node> nexts;
        public ArrayList<Edge> edges;
        public Node(int value){
            this.value = value;
            this.in = 0;
            this.out = 0;
            this.nexts = new ArrayList<>();
            this.edges = new ArrayList<>();
        }
    }

    public static  class  Edge{
        public Node from;
        public Node to;
        public int weight;
        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static Graph createGraph(Integer[][] matrix){
        Graph graph = new Graph();
       for(int i = 0; i < matrix.length; i++){
          Integer form= matrix[i][0];
          Integer to= matrix[i][1];
          Integer weight= matrix[i][2];
         if(!graph.nodes.containsKey(form)){
             graph.nodes.put(form, new Node(form));
         }
         if(!graph.nodes.containsKey(to)){
             graph.nodes.put(to, new Node(to));
         }
         Node fromNode = graph.nodes.get(form);
         Node toNode = graph.nodes.get(to);
         Edge edge = new Edge(fromNode, toNode, weight);
         fromNode.edges.add(edge);
         fromNode.nexts.add(toNode);
         fromNode.out++;
         toNode.in++;
         graph.edges.add(edge);
       }
       return graph;
    }

    public static void bfs(Node node){
        if(node==null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> visited = new HashSet<>();
        queue.add(node);
        while(!queue.isEmpty()){
            Node current = queue.poll();
            System.out.println(current.value);
            visited.add(current);
            for (Node next : current.nexts) {
                if(!visited.contains(next)){
                    queue.add(next);
                }
            }
        }
    }

    public static void dfs(Node node,HashSet<Node> visited){
        if(node==null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()){
            Node current = stack.pop();
            System.out.println(current.value);
            visited.add(current);
           for(Node next : current.nexts){
               if(!visited.contains(next)){
                   stack.push(next);
                   break;
               }
           }
        }
    }

    public static void dfsMethod2(Node node,HashSet<Node> visited){
        if(node==null){
            return;
        }
        System.out.println(node.value);
        visited.add(node);
        for(Node next : node.nexts){
            if(!visited.contains(next)){
                dfsMethod2(next,visited);
            }
        }
    }

    public static ArrayList<Node> topSort(Graph graph){
         HashMap<Node,Integer> inMap = new HashMap<>();
         Queue<Node> zeroQueue = new LinkedList<>();
         for(Node node : graph.nodes.values()){
             inMap.put(node,node.in);
             if(node.in==0){
                 zeroQueue.add(node);
             }
         }
         ArrayList<Node> result = new ArrayList<>();
         while(!zeroQueue.isEmpty()){
             Node cur = zeroQueue.poll();
             result.add(cur);
          for(Node next : cur.nexts){
             inMap.put(next,inMap.get(next)-1);
             if(inMap.get(next)==0){
                 zeroQueue.add(next);
             }
          }
         }
         return result;
    }



    public  static class  Mysets {
        public HashMap<Node, List<Node>> setMap;

        public Mysets(List<Node> nodes) {
            setMap = new HashMap<>();
            for (Node node : nodes) {
                List<Node> set = new ArrayList<>();
                set.add(node);
                setMap.put(node, set);
            }
        }

        public  boolean isSameSet(Node from, Node to) {
            List<Node> nodes1 = setMap.get(from);
            List<Node> nodes2 = setMap.get(to);
         return nodes1==nodes2;
        }
        public void union(Node from, Node to){
            List<Node> formSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            for (Node node : toSet) {
                formSet.add(node);
            }
            setMap.put(to, formSet);
        }
    }

    public static Set<Edge> kruskal(Graph graph){
        List<Node> nodes=new ArrayList<>();
       graph.nodes.forEach((k,v)->{
           nodes.add(v);
       });
       Mysets mysets = new Mysets(nodes);
      PriorityQueue<Edge> minHeap = new PriorityQueue<>(new Comparator<Edge>() {
          @Override
          public int compare(Edge o1, Edge o2) {
              return o1.weight - o2.weight;
          }
      });
     for(Edge edge: graph.edges ){
         minHeap.add(edge);
     }
     Set<Edge> result = new HashSet<>();
     while(!minHeap.isEmpty()){
         Edge edge = minHeap.poll();
         if(!mysets.isSameSet(edge.from, edge.to)){
             mysets.union(edge.from, edge.to);
             result.add(edge);
         }
     }
     return result;
    }

    public  static  Set<Edge> prim(Graph graph,int first){
         Set<Edge> result = new HashSet<>();
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(new Comparator<Edge>(){
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
        HashSet<Node> union=new HashSet<>();
        union.add(graph.nodes.get(first));
        for (Edge edge : graph.nodes.get(first).edges){
           minHeap.add(edge);
        }
       while(!minHeap.isEmpty()){
           Edge edge = minHeap.poll();
         if(!(union.contains(edge.to))){
             result.add(edge);
             union.add(edge.to);
             for(Edge nextEdge:edge.to.edges){
                 minHeap.offer(nextEdge);
             }
         }
       }
        return result;
    }

    public static HashMap<Node,Integer> dijkstra(Node head){
     HashMap<Node,Integer> distanceMap = new HashMap<>();
     distanceMap.put(head,0);
     HashSet<Node> visited = new HashSet<>();
     Node minNode=getMinDistanceAndUnVisited(distanceMap,visited);
     while(minNode!=null){
         int distance = distanceMap.get(minNode);
         for (Edge edge : minNode.edges){
             Node toNode = edge.to;
              if(!distanceMap.containsKey(toNode)||distanceMap.get(toNode)>distanceMap.get(minNode)+ edge.weight){
                  distanceMap.put(toNode,distanceMap.getOrDefault(minNode,distanceMap.get(minNode))+ edge.weight);
              }
         }
         minNode=getMinDistanceAndUnVisited(distanceMap,visited);
     }
     return distanceMap;
    }

    public  static Node getMinDistanceAndUnVisited(HashMap<Node,Integer> distanceMap,HashSet<Node> visited){
        Integer minDistance = Integer.MAX_VALUE;
        Node minNode = null;
        for (Map.Entry<Node, Integer> nodeIntegerEntry : distanceMap.entrySet()) {
            int distance = nodeIntegerEntry.getValue();
            Node node=nodeIntegerEntry.getKey();
            if(!visited.contains(node)){
                visited.add(node);
                minDistance = Math.min(minDistance,distance);
                minNode = node;
            }
        }
      return minNode;
    }

    public static void main(String[] args) {
         Integer[][] matrix ={{1,2,100},{2,3,200},{3,4,300}};
        Graph graph = createGraph(matrix);
        Set<Edge> kruskal = prim(graph,1);
        HashMap<Node, Integer> dijkstra = dijkstra(graph.nodes.get(1));
        System.out.println(dijkstra);
    }
}

