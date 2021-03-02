package zombiepei;

public class BST {
    CharNode root;

    public BST() {
    }

    public BST(CharNode root) {
        this.root = root;
    }

    public boolean insert(char ch){
        if(root == null) {
            root = new CharNode(ch, 1);
            return true;
        }

        CharNode curr = root;
        while(true){
            if(curr.getCh() == ch){
                curr.setFreq(curr.getFreq() + 1);
                return false;   // 没有插入新结点
            }else if(curr.getCh() < ch){
                if(curr.right != null){
                    curr = curr.right;
                }else{
                    curr.right = new CharNode(ch, 1);
                    break;
                }
            }else{
                if(curr.left != null){
                    curr = curr.left;
                }else{
                    curr.left = new CharNode(ch, 1);
                    break;
                }
            }
        }
        return true;
    }

    public void show(){
        inorderTraverse(root);
    }

    private void inorderTraverse(CharNode root){
        if(root == null) return;

        inorderTraverse(root.left);
        System.out.println(root);
        inorderTraverse(root.right);
    }
}
