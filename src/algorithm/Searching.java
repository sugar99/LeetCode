package algorithm;

import java.util.LinkedList;
import java.util.Scanner;

public class Searching {

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 2, 4, 4};
        int index = binarySearchIndexAfter(arr, 0, arr.length, 2);
        System.out.println(-index - 1);

    }

    public static int binarySearch(int[] nums, int fromIndex, int toIndex, int target){
        if(nums.length == 0) return -1;

        int left = fromIndex;
        int right = toIndex - 1;

        while(left <= right){
            int mid = left + (right - left) / 2;
            int midVal = nums[mid];
            if(target < midVal){
                right = mid - 1;
            }else if(target > midVal){
                left = mid + 1;
            }else{
                return mid;
            }
        }
        int insertionPoint = left;
        return -(insertionPoint + 1);
    }

    public static int binarySearchIndexAfter(int[] nums, int fromIndex, int toIndex, int target){
        if(nums.length == 0) return -1;

        int left = fromIndex;
        int right = toIndex - 1;

        while(left <= right){
            int mid = left + (right - left) / 2;
            int midVal = nums[mid];
            if(target < midVal){
                right = mid - 1;
            }else if(target > midVal){
                left = mid + 1;
            }else{     // 不返回，left右移，逼近右边界的插入点
                left = mid + 1;
            }
        }
        int insertionPoint = left;
        return -(insertionPoint + 1);
    }

    public static int binarySearchIndexBefore(int[] nums, int fromIndex, int toIndex, int target){
        if(nums.length == 0) return -1;

        int left = fromIndex;
        int right = toIndex - 1;

        while(left <= right){
            int mid = left + (right - left) / 2;
            int midVal = nums[mid];
            if(target < midVal){
                right = mid - 1;
            }else if(target > midVal){
                left = mid + 1;
            }else{     // 不返回，right左移，逼近左边界的插入点
                right = mid - 1;
            }
        }
        int insertionPoint = right;
        return -(insertionPoint + 1);
    }

    public static void DFS(Node root){
        if(root == null) return;

        LinkedList<Node> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()){
            Node node = stack.removeLast();
            if(node.left != null){
                // operations
                stack.addLast(node.left);
            }
            if(node.right != null){
                // operations
                stack.addLast(node.right);
            }
            stack.addLast(node);

        }
    }

    public void BFS(Node root){
        if(root == null) return;



    }
    

    static class Node{
        int val;
        Node left;
        Node right;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
