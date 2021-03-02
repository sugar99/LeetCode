package zombiepei;

import java.io.*;

public class BSTTest {
    public static void main(String[] args) {
        String fileUrl = "C:/Users/SUGAR/Desktop/英语阅读.txt"; // 填自己的文件路径

        BST bst = new BST();
        InputStreamReader reader = null;
        BufferedReader bReader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(new File(fileUrl)));
            bReader = new BufferedReader(reader);
            String line = "";
            line = bReader.readLine();
            while(line != null){
                for(char ch : line.toCharArray()) {
//                    if(ch >= 'A' && ch <= 'z'){ // 只统计字母
                        bst.insert(ch);
//                    }
                }
                line = bReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bReader != null) {
                try {
                    bReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        bst.show();
    }



    void testBSTInsert(){
        CharNode root = new CharNode('d', 1);
        root.setLeft(new CharNode('c', 2));
        root.setRight(new CharNode('e', 3));
        BST bst = new BST(root);
        bst.show();
        System.out.println();
        bst.insert('a');
        bst.insert('c');
        bst.insert('z');
        bst.insert('e');
        bst.show();
    }

    void testBSTShow(){
        CharNode root = new CharNode('d', 1);
        root.setLeft(new CharNode('c', 2));
        root.setRight(new CharNode('e', 3));
        BST bst = new BST(root);
        bst.show();
    }
}
