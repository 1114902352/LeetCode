package com.design.pattern.decorator;

/**
 * װ�ν�ɫ
 * 
 * @author Administrator
 *
 */
public class Decorator implements Programmer {
	private Programmer programmer;

	public Decorator(Programmer programmer) {
		this.programmer = programmer;
	}

	@Override
	public void programme() {
		programmer.programme();
		// ���ӵ����λ��߹���
	}
}