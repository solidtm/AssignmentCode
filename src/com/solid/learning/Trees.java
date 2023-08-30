package com.solid.learning;

/*
*  Tree: A data structure that store data in a hierarchical structure eg: a family tree
*
*  Key things to note when working with trees:
*  1. A tree store values as nodes in the tree, each item in the tree is called a node
*  2. The node without a parent is called the root node.
*
*                       1  -> root node   => Binary tree 0
*                      / \
*                     2   3     1  pre - nlr
*                    / \   \       in - lnr
*                   4   5   8   2  post - lrn
*                    \       \
*                     7       9 -> leaf nodes (5 is also a leaf node) 3
*     8
*      \
*       9
*  Operations in a tree:
*  1. insert a node -> Fill the tree from the left
*  2. delete a node
*  3. traverse a tree... (we will visit briefly)
*
* */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Trees {
    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.insert( 1);
        tree.insert( 2);
        tree.insert( 3);
        tree.insert( 4);
        tree.insert( 5);
        tree.insert( 6);
        tree.insert( 7);
        tree.insert( 8);
        tree.insert( 9);

        tree.displayTree();
        tree.traverseInOrder();
        tree.delete(6);
        tree.delete(4);
        tree.displayTree();
    }
}

class Tree{
    TreeNode rootNode;

    public Tree() {
        this.rootNode = null;
    }

    //insert
    public void insert(int data){
        TreeNode newNode = new TreeNode(data);

        if(rootNode == null){
            rootNode = newNode;
            return;
        }

        TreeNode current = rootNode;
        TreeNode parent;

        while(true){
            parent = current;
            if(data < current.value){
                current = current.left;
                if(current == null){
                    parent.left = newNode;
                    return;
                }
            }else{
                current = current.right;
                if(current == null){
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    //delete
    public void delete(int data){
        TreeNode current = rootNode;
        TreeNode parent  = rootNode;
        boolean isLeftChild = true;

        while(current.value != data){
            parent = current;
            if(data < current.value){
                current = current.left;
                isLeftChild = true;
            }else{
                current = current.right;
                isLeftChild = false;
            }
            if(current == null){
                return;
            }
        }

        if(current.left == null && current.right == null){
            if(current == rootNode){
                rootNode = null;
            }else if (isLeftChild){
                parent.left = null;
            }else{
                parent.right = null;
            }
        }else if(current.right == null){
            if(current == rootNode){
                rootNode = current.left;
            }else if (isLeftChild){
                parent.left = current.left;
            }else{
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if(current == rootNode){
                rootNode = current.right;
            }else if (isLeftChild){
                parent.left = current.right;
            }else{
                parent.right = current.right;
            }
        }else{
            TreeNode successor = getSuccessor(current);
            if(current == rootNode){
                rootNode = successor;
            }else if(isLeftChild){
                parent.left = successor;
            }else{
                parent.right = successor;
            }
            successor.left = current.left;
        }
    }

    private TreeNode getSuccessor(TreeNode node) {
        TreeNode parentSuccessor = node;
        TreeNode successor  = node;
        TreeNode current  = node.right;

        while(current != null){
            parentSuccessor = successor;
            successor = current;
            current = current.left;
        }

        if(successor != node.left){
            parentSuccessor.left = successor.right;
            successor.right = node.right;
        }

        return successor;
    }

    //traverse
    //There are 2 types of tree traversal
    //1. Breadth First Search/Traversal
    //2. Depth First Search/Traversal

    public void traverseInOrder(){
        if(rootNode == null){
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = rootNode;

        while(!stack.isEmpty() || current != null){
            while(current != null){
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            System.out.println(current.value + " ");
            current = current.right;
        }

        System.out.println();
    }

    public void displayTree(){
        if(rootNode == null){
            System.out.println("Tree is empty");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(rootNode);

        while(!queue.isEmpty()){
            int levelSize = queue.size();

            for(int i = 0; i < levelSize; i++){
                TreeNode current = queue.poll();

                if(current != null){
                    System.out.println("\t\t" + current.value);
                    System.out.println("\t   " + "/" + " " + "\\");
                    if(current.left != null) queue.offer(current.left);
                    if(current.right != null) queue.offer(current.right);
                }
            }
            System.out.println();
        }
    }
}

/*    Breadth first traversal  BFS   nlr       lnr         lrn
      Depth first traversal DFS => Preorder, Inorder and Postorder
*
*                        1  -> root node   => Binary tree 0
 *                      / \
 *                     2   3     1
 *                    / \   \
 *                   4   5   6   2      4 -> 7 -> 2 -> 5 -> 1 -> 8 -> 6 -> 9 -> 3
 *                    \     / \
 *                     7   8   9 -> leaf nodes (5 is also a leaf node) 3
*
*
* */

class TreeNode{
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
        left = null;
        right = null;
    }
}
