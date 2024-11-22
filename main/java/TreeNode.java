import org.w3c.dom.Node;

import java.util.*;

public   class  TreeNode {
    public  int val;
    public TreeNode left;
    public TreeNode right;


    public void preOrder(TreeNode tree){
        Stack<TreeNode> stack = new Stack<>();
        stack.push(tree);
        while(!stack.isEmpty()){
           TreeNode temp = stack.pop();
            System.out.println(temp.val);
            if (temp.right != null)
            stack.push(temp.right);
            if(temp.left != null) {
                stack.push(stack.pop().left);
            }
        }
    }

    public void postOrder(TreeNode tree){
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack1 = new Stack<>();
        stack.push(tree);
        while(!stack.isEmpty()){
            TreeNode temp = stack.pop();
            stack1.push(temp);
            if(temp.left != null){
                stack.push(stack.pop().left);
            }
            if (temp.right != null)
                stack.push(temp.right);
        }
        while(!stack1.isEmpty()){
            System.out.println(stack1.pop().val);
        }
    }

    public void inOrder(TreeNode tree){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = tree;
        while(!stack.isEmpty()){
            if(temp!=null){
                stack.push(temp);
                stack.push(temp.left);
            } else{
                temp = stack.pop();
                System.out.println(temp.val);
                temp = temp.right;
            }
        }
        Stack<Integer> stack1=new Stack<>();
    }


    public static void preOrderRecur(TreeNode root){
        if(root==null){
            return;
        }
        System.out.println(root.val);
         preOrderRecur(root.left);
        preOrderRecur(root.right);
    }


    public static boolean isBST(TreeNode root){
        if(root==null){
            return true;
        }
      if(!isBST(root.left)){
          return false;
      }
      if(root.val<=root.left.val){
          return false;
      }
      return isBST(root.right);
    }

    public static class result{
        boolean isAVL;
        int height;
        int min;
        int max;
        public result(boolean isAVL, int height, int min, int max){
            this.isAVL = isAVL;
            this.height = height;
        }
    }

    public static  result process1(TreeNode root){
        if (root == null) {
            return new result(true,0,Integer.MAX_VALUE,Integer.MIN_VALUE);
        }
        result leftRusult = process1(root.left);
        result rightRusult = process1(root.right);
    boolean isHeightMeet=Math.abs(leftRusult.height- rightRusult.height)<2;
    boolean isBST=root.val> leftRusult.max&&root.val<rightRusult.min;
    boolean isAVL=isHeightMeet&&isBST&&leftRusult.isAVL&& rightRusult.isAVL;
    int height=Math.max(leftRusult.height,rightRusult.height)+1;
    int min=leftRusult.min;
    int max=rightRusult.max;
        return new result(isAVL,height,min,max);
    }

    public static boolean isAVL(TreeNode root){
        return process1(root).isAVL;
    }




    public static void main(String[] args) {

    }

}


