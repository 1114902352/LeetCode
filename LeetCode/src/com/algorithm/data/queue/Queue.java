package com.algorithm.data.queue;

import java.util.Stack;

/**
 * ��һ��ջ�ṹʵ��һ������
 * ԭ����ʹ���������л��൹������
 * pushʱ��ֻ����stack1,popʱ����stack2��;��stack2��ֵʱ����stack1��ֵ�˸�stack2.
 */
public class Queue {
	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();

	// Push element x to the back of queue.
	public void push(int x) {
		stack1.push(x);
	}

	// Removes the element from in front of queue.
	public void pop() {
		if (!stack2.isEmpty())
			stack2.pop();
		else {
			while (!stack1.isEmpty())
				stack2.push(stack1.pop());
			stack2.pop();
		}
	}

	// Get the front element.
	public int peek() {
		if (!stack2.isEmpty())
			return stack2.peek();
		else {
			while (!stack1.isEmpty())
				stack2.push(stack1.pop());
			return stack2.peek();
		}
	}

	// Return whether the queue is empty.
	public boolean empty() {
		return stack1.empty() && stack2.empty();
	}

}