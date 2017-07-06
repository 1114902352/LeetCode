package com.design.pattern.decorator;

public class Client {

	public static void main(String[] args) {
		// ������������ ���� ->����һ������ĳ���Ա
		// ����������ֻ�б������ ����ô��
		// ���븳������������� ��Ȼ��ô׬��Ǯ!
		// ���� �ϵ۸���װ����һ�� ˲������������B��
		Programmer programmer = new Tom();

		// װ����ǳ� ��˿ ����װ���� ������и��������
		Decorator hacker = new Hacker(programmer);
		// �������������˿����Ա�;��кڿ͵ļ��ܰ���
		// ���¾�û��˭�ҽ�����˿�˰� ���� ��Ȼ�ַ���������Ա���
		System.out.println("��һ��װ��");
		hacker.programme();

		// ����Ա�������� ����Ҫ����ļ��� ��Ϊ��Ҫ��Ϯ
		// �����ϵ��ٸ���װ����һ��
		// �������кڿͼ��ܵĻ��������⸳�������ܹ�ʦ�Ĺ���
		System.out.println("--------------�ڶ���װ��");
		Decorator achitect = new SoftwareArchitect(hacker);

		achitect.programme();
		// Ҳ����һ��װ���������� ��Ϊ�����й�ͬ�ĸ�����󹹼��ӿ� Programmer
		System.out.println("------------һ��װ����������");
		Decorator achitect1 = new SoftwareArchitect(new Hacker(new Tom()));
		achitect1.programme();
	}

}
