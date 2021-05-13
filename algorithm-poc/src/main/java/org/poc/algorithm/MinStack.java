package org.poc.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现一个栈，具有入栈、出栈、获取栈最小值的功能。
 */
public class MinStack {

    private List<Integer> data = new ArrayList<Integer>();
    private List<Integer> mins = new ArrayList<Integer>();

    public void push(int number) {
        data.add(number);
        Integer min = getMin();
        if (min == null || number < min) {
            mins.add(data.size() - 1);
        }
    }

    public Integer pop() {
        if (data.size() == 0) {
            return null;
        }
        int dataIndex = data.size() - 1;
        Integer value = data.get(dataIndex);
        data.remove(dataIndex);
        if (dataIndex == mins.get(mins.size() - 1)) {
            mins.remove(mins.size() - 1);
        }
        return value;
    }

    public Integer getMin() {
        if (mins.size() == 0) {
            return null;
        }
        return data.get(mins.get(mins.size() - 1));
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(1);
        stack.push(1);
        stack.push(1);
        System.out.println(stack.getMin());
        stack.pop();
        stack.pop();
        System.out.println(stack.getMin());
        System.out.println(stack.getMin());
        System.out.println(stack.getMin());

    }


}
