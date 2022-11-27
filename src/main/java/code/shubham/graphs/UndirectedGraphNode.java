package code.shubham.graphs;

import java.util.List;

import java.util.ArrayList;

public  class UndirectedGraphNode {
      public int label;
      public List<UndirectedGraphNode> neighbors;
      public UndirectedGraphNode(int x) {
          this.label = x;
          this.neighbors = new ArrayList<UndirectedGraphNode>();
      }
  }
