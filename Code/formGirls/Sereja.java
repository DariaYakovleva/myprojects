package ru.ifmo.neerc.java.AA;
import ch.melnikov.test.*;   //���� ��������� �������
import static ru.ifmo.neerc.java.AA.PI;

public class AA extends B {
    ch.melnikov.test.AA a;
    ru.ifmo.neerc.java.AA a2;
    AA c;                 //��������� ���, ������� ��� ���������, �. �. ������ ����

    public int x1;
    private int x2;
    protected int x3;
    /*package local*/int x3; //����� ������ � ����� ������ ��� ����������� � ������

    //��������� �����
    //������ ���� ����� ��� ���� ����������� ������. ������ ������ �� �������� ��������� ������.
    final int x = 0;
    void run() {
        new If() {
             public void run() {
            }
        }.run();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println(x); //����   x ������� � final, �� �� ����� �� ������������
            }
        })
    }

     //��������� ������
// � B ������ A. ������ � ������� ���������� B ���� ���� A. ��������, ���� ������� � A x �� B, ��, 
//��� ������ B �, ��������������, ������ x, ����� ������ �����.

public class B extnds AA {
	static class C {
		int y;
		void f() {
			System.err.println(x);
		}
	}
	ru.ifmo.neerc.java.B.C c = new C();
	int x;
	void r() {
		B b = new B(1);
		b.C.y = 10;
	}

	
	public B(nt x) {
		this.x = x;
	}


	int  x = 13;
        class A {
//��� �� ����� ���� ������ �����
        	int x = 10;
		void a() {
			System.out.println(x);    //10
			System.err.println(this.x);   //10
			System.out.println(B.this.x);    // 13
			System.err.println(((AA)B.this).x);   //11
			System.err.println(B.super.x);            //11		
		}
        }

	//� � ������ B. A ������  ��������� �� B

	void run() {
		A a = new A();

	}



 
}