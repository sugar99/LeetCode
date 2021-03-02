package zombiepei;

import java.util.Objects;

public class CharNode {
    char ch;
    int freq;
    CharNode left;
    CharNode right;

    public CharNode() {
    }

    public CharNode(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    public CharNode(char ch, int freq, CharNode left, CharNode right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "[" + ch + " : " + freq + "]";
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }


    public CharNode getLeft() {
        return left;
    }

    public void setLeft(CharNode left) {
        this.left = left;
    }

    public CharNode getRight() {
        return right;
    }

    public void setRight(CharNode right) {
        this.right = right;
    }
}
