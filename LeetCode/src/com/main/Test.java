package com.main;

import java.util.ArrayList;
import java.util.List;

public class Test {

	static class HeapOutOfMemory{}

	public static void main(String[] args) {
		List<HeapOutOfMemory> list = new ArrayList<>();
		for(int i=0;i<60000;i++){
			list.add(new HeapOutOfMemory());
		}
	}
}
