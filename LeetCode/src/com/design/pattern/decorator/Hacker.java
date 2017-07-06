package com.design.pattern.decorator;

/**
 * ����װ�ν�ɫ �ڿ��� �����и��ӵĹ��� �������ֱ��˵ĵ���
 * 
 * @author Administrator
 *
 */
public class Hacker extends Decorator {

	public Hacker(Programmer programmer) {
		super(programmer);
	}

	@Override
	public void programme() {
		super.programme();
		// ���ӵ����λ��߹���
		System.out.println("�Ҿ��кڿ͵ļ���   �������ֱ��˵ĵ���");
	}

}
