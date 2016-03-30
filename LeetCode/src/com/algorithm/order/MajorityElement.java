package com.algorithm.order;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MajorityElement {

	public static int majorityElement(int[] nums) {
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int num:nums){
			if(map.containsKey(num)){
				map.put(num,map.get(num)+1);
			}else{
				map.put(num,1);
			}
		}
		Iterator<Integer> it = map.keySet().iterator();
		while(it.hasNext()){
			Integer key = it.next();
			if(map.get(key)>nums.length/2){
				return key;
			}
		}
        return 0;
    }
}
