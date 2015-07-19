/* Landon Speer */

package lab8;

/* Implements a FIFO-Priority Queue using two LIFO Stacks.
 * offer: add an element to this queue, return true if element is added successfully, return false otherwise
 * priorityPoll: removes and returns the element with the highest priority.
 * elementPoll:  removes and returns the element at the front of the queue.
 * peek: returns the element at the front of the queue.              
 */

import java.util.*;

public class BinaryPollQueue<E extends Comparable<E>> { 
    private Stack<E> stk1;
    private Stack<E> stk2;
    
    public BinaryPollQueue(){
        this.stk1 = new Stack<E>();
        this.stk2 = new Stack<E>();
    }//constructor
    
    public boolean offer(E e){
        try{
        stk1.push(e);
        return true;
        }//try
        catch(NullPointerException t){
            t.printStackTrace();
            return false;
        }//catch
    }//offer
    
    public E peek(){
        E e = null;
        if(stk1.size() == 0){
            return null;
        }//if
        else{
            while(!stk1.empty()){
                stk2.push(stk1.pop());
                e = stk2.peek();
            }//while
            while(!stk2.empty()){
                stk1.push(stk2.pop());
            }//while
        }//else
        return e;
    }//peek
    
    
    public E elementPoll(){
        E e = null;
        if(stk1.size() == 0){
            return null;
        }//if
        else{
            while(!stk1.empty()){
                stk2.push(stk1.pop());
            }//while
            e = stk2.pop();
            while(!stk2.empty()){
                stk1.push(stk2.pop());
            }//while
        }//else
        return e;
    }//elementPoll
    
    
    public E priorityPoll(){
        E max = stk1.peek();
        E e = null;
        if(stk1.size() == 0){
            return null;
        }//if
        else{
            while(!stk1.empty()){
                stk2.push(stk1.pop());
                if(stk2.peek().compareTo(max) > 0){
                    max = stk2.peek();
                }//if
            }//while
            while(!stk2.empty()){
                if(stk2.peek().equals(max)){
                    stk1.push(stk2.pop());
                    e = stk1.pop();
                }//if
                else{
                    stk1.push(stk2.pop());
                }//else
            }//while
        }//else
        return e;
    }//priorityPoll
    
    /**
     * @return: returns an ArrayList of the objects left in stk1
     * 
     * @requires: an array list to hold the objects
     * @ensures: an array list of objects will be returned to the user
     * This method will go through the stacks and when it pushes the objects back
     * to stack1 it will add that value to the list first so you get the true 
     * order of the stack.
     */
    
    public ArrayList<E> toDisplay(){
        ArrayList<E> al = new ArrayList<E>();
        if(stk1.size() == 0){
            return null;
        }//if
        else{
            while(!stk1.empty()){
                stk2.push(stk1.pop());
            }//while
            while(!stk2.empty()){
                stk1.push(stk2.pop());
                al.add(stk1.peek());
            }//while
        }//else
        return al;
    }//toDisplay
    
}//class


/*
Outputs: 

run:
Front Element  <c,2> 
Removed Highest Priority <e,5> 
Front Element  <c,2> 
Removed Front <c,2> 

Front Element  <d,3> 
Removed Front <d,3> 
Removed Highest Priority <h,7> 

BUILD SUCCESSFUL (total time: 1 second)


*/