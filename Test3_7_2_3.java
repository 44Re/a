package chapter7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test3_7_2_3 {

	/**
	 * @param args
	 *
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader a = new BufferedReader(new InputStreamReader(System.in));

		try {
			int count = 0;
			int x = (int) (Math.random() * 101);
			System.out.println(x);
			String b = a.readLine();
			int num = 0;
			while ((num = Integer.parseInt(b)) != x) {
				if (count == 9) {
					break;

				} else if (num > x) {
					System.out.println("x�����傫�������ł�");
				} else if (num < x) {
					System.out.println("x���������������ł�");
				}
				count++;
				b = a.readLine();
			}
			if (num == x) {
				System.out.println("����");
				System.out.println("��������܂ł�" + (count + 1) + "�񂩂���܂���");
			} else {
				System.out.println("10�񎸔s");
			}
		} catch (NumberFormatException o) {
			System.out.println("�����ȊO�����͂���Ă��܂��B");
		}

	}
}
