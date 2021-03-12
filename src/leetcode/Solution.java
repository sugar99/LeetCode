package leetcode;


import java.lang.reflect.Method;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Solution {

    public static void main(String[] args) throws Exception{
        int[] test = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(test));
    }


    public static int maxArea(int[] height) {
        int ans = 0;
        int l = 0;
        int r = height.length - 1;
        while(l < height.length && r >= 0){
            int min = Math.min(height[l], height[r]);
            int len = r - l;
            ans = Math.max(ans, min * len);
            if(min == l){
                int lnext = l + 1;
                while(lnext < r && height[lnext] <= height[l]){
                    lnext++;
                }
                if(lnext == r){
                    break;
                }
                l = lnext;
            }else{
                int rnext = r - 1;
                while(rnext > l && height[rnext] <= height[r]){
                    rnext--;
                }
                if(rnext == l){
                    break;
                }
                r = rnext;
            }
        }
        return ans;
    }

    class Inner{
        public void say() {
            System.out.println("hello");
        }
    }


}

class test{
    void say() {
        System.out.println("hello");
    }
}

class Task {
    volatile static int printCount = 0;
    static ReentrantLock lock = new ReentrantLock();
    static Condition cA = lock.newCondition();
    static Condition cB = lock.newCondition();
    static Condition cC = lock.newCondition();
}
class TaskA extends Task implements Runnable{
    @Override
    public void run() {
        while (printCount < 100){
            lock.lock();
            if(printCount < 100){
                System.out.println("A");
                printCount++;
            }

            cB.signal();
            try {
                cA.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
class TaskB extends Task implements Runnable{
    @Override
    public void run() {
        while(printCount < 100){
            lock.lock();
            if(printCount < 100){
                System.out.println("B");
                printCount++;
            }
            cC.signal();
            try {
                cB.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
class TaskC extends Task implements Runnable{
    @Override
    public void run() {
        while (printCount < 100){
            lock.lock();
            if(printCount < 100){
                System.out.println("C");
                printCount++;
            }

            cA.signal();
            try {
                cC.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }


    }
}




