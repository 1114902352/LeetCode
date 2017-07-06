package com.design.pattern.decorator;

/**
 * ����װ�ν�ɫ ����ܹ�ʦ�� �����и��ӵĹ��� ������ܸ���վ��ϵͳ�Ĺ�
 * 
 * @author Administrator
 *
 */
public class SoftwareArchitect extends Decorator {

	public SoftwareArchitect(Programmer programmer) {
		super(programmer);
	}

	@Override
	public void programme() {
		super.programme();
		// ���ӵ����λ��߹���
		System.out.println("�Ҿ��мܹ�ʦ�ļ���  ��������ܸ���վ��ϵͳ�ĹǼ�");
	}
}
