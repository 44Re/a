package chapter6;

public class Athlete {
	String game;
	String land;
	int num;

	public Athlete(String g,String l,int n){
		game=g;
		land=l;
		num=n;

	}
	public void print(){
		System.out.println("���Z��:"+game);
		System.out.println("��:"+land);
		System.out.println("�w�ԍ�:"+num);
		System.out.println();
	}



}
