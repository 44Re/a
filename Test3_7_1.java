package chapter7;

import java.io.*;

public class Test3_7_1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		BufferedReader a = new BufferedReader(new InputStreamReader(System.in));

		try {
			String b = a.readLine();

			int num = 0;
			while ((num = Integer.parseInt(b)) != 0) {

				if (num % 15 == 0) {
					System.out.println("FizzBuzz");
				} else if (num % 3 == 0) {
					System.out.println("Fizz");
				} else if (num % 5 == 0) {
					System.out.println("Buzz");
				} else {
					//System.out.println("(*>�~<*)");
				}
				b = a.readLine();
			}

			System.out.println("�I��");
		} catch (NumberFormatException o) {
			System.out.println("�����ȊO�����͂���Ă��܂��B");
		}

	}
}
