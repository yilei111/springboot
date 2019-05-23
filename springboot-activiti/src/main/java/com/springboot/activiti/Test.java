package com.springboot.activiti;

public class Test {

	public static void main(String[] args) {
		System.out.println(menthod());;
		
	}
	
	@SuppressWarnings("finally")
	public static int menthod() {
		int a = 4;
		try {
			a=9;System.out.println(a);
			return a;
		}finally {
			a= 6;
			return a;
		}
	}
}
