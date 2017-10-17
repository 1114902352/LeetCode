package com.algorithm.data.queue;

import java.util.Stack;

/**
 * 用一个栈结构实现一个队列
 * 原理：使用两个队列互相倒换数据
 * push时，只进入stack1,pop时，从stack2退;当stack2无值时，将stack1的值退给stack2.
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
