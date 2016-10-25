package chapter7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CopyOfCopyOfTest3_7_34 {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		BufferedReader read = new BufferedReader(new InputStreamReader(
				System.in));
		int num = 5;
		int count = 0;
		int[] user = new int[num];
		int[] answer = getRandomNum(num);

		print(answer);

		System.out.println("5���̐�������͂��Ă�������");

		String str = "";
		retry: while (count < 11) {// �Ȃɂ���������߂�
			if (count < 10) {
				str = read.readLine();
			}
			count++;

			gameOver(count);
			{
				if (count == 11) {
					break;
				}
			}
			// �����񂪓��͂���Ă��Ȃ������ׂ�
			if (determineIfNumber(str)) {
				continue retry;
			}

			if (isKeta(str, num)) {
				continue retry;
			}

			System.out.print("user:");

			print(user = conversion(str, user));

			if (isOverlap(user)) {
				System.out.println("0�`9�܂ł̐��l��5���ŏd�����Ȃ��悤�ɓ��͂��Ă��������B");
				continue retry;

			} // �q�b�g�̔���
			int hit = 0;
			hit = hit(user, answer, hit);
			// �u���E����
			int blow = 0;
			blow = blow(user, answer, blow);

			// ����
			result(hit, blow, answer, count);
			{
				if (hit == answer.length && count <= 10) {
					break;
				}
			}

		}
	}

	// �����񂪓��͂���Ă��Ȃ������ׂ�
	public static boolean determineIfNumber(String str) {
		boolean flag = false;
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			System.out.println("�����񂪓��͂���Ă��܂�");
			flag = true;
		}
		return flag;

	}

	// �z��̗v�f�����ׂďo�͂���
	public static void print(int[] array) {
		for (int i : array) {
			System.out.print(i);
		}
		System.out.println();
	}

	// �����̐����P(0~9�܂ł̐��l���V���b�t��)
	public static int[] getShuffleNum() {
		int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		// array���V���b�t��
		for (int i = 0; i < array.length; i++) {
			// 0�`9�܂ł̗���
			int ran = (int) (Math.random() * 10);
			int a = array[i];
			array[i] = array[ran];
			array[ran] = a;
		}
		return array;

	}

	// �����̐����Q(num���̐��l�ɂ���)
	public static int[] getRandomNum(int keta) {
		int[] answer = new int[keta];
		int[] array = getShuffleNum();

		// ��ŃV���b�t�������l��0�`num�Ԗڂ̒l���擾����
		for (int i = 0; i < answer.length; i++) {
			answer[i] = array[i];
		}
		// ��������0�������ꍇ�͈�Ԃ��K�̒l������
		if (answer[0] == 0) {
			answer[0] = array[keta];
		}
		return answer;

	}

	/**
	 * ���͂��ꂽ���l�̌��̐��ۂ𔻒f���郁�\�b�h�ł�
	 *
	 * @param str
	 *            ���͂��ꂽ������
	 * @param num
	 *            ����������
	 * @return ���͂������l�̌�����������true�A�Ⴄ��false��Ԃ��܂�
	 */
	public static boolean isKeta(String str, int num) {
		if (str.length() > num) {
			System.out.println("�����������܂��B");
			return true;

		} else if (str.length() < num) {
			System.out.println("�������Ȃ����܂��B");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ���͂��ꂽ���l�ɏd�����Ȃ����̃`�F�b�N������
	 *
	 * @param user���͂��ꂽ���l�̔z��
	 * @return�@�d���������true�A�Ȃ����false��Ԃ��܂�
	 */
	// ���͂������l�ɏd�����Ȃ����̃`�F�b�N
	public static boolean isOverlap(int[] user) {
		for (int i = 0; i < user.length; i++) {
			for (int j = i + 1; j < user.length; j++) {
				if (user[i] == user[j]) {
					return true;
				}
			}
		}
		return false;
	}

	// �P�O�񎸔s����ƃQ�[���I�[�o�[�Əo��
	public static void gameOver(int count) {
		if (count == 11) {
			System.out.println();
			System.out.println(count - 1 + "�񎸔s�I�Q�[���I�[�o�[");
		}
	}

	// ���͂��ꂽString�^�̐��l��int�^�ɕϊ�����
	public static int[] conversion(String str, int[] user) {
		for (int j = 0; j < str.length(); j++) {
			char ch = str.charAt(j);
			user[j] = Character.getNumericValue(ch);
		}
		return user;
	}

	// hit�̔���
	public static int hit(int[] user, int[] answer, int hit) {
		for (int i = 0; i < user.length; i++) {
			if (user[i] == answer[i]) {
				hit++;
			}
		}
		return hit;
	}

	// blow�̔���
	public static int blow(int[] user, int[] answer, int blow) {
		for (int i = 0; i < user.length; i++) {
			for (int j = 0; j < answer.length; j++) {
				if (user[i] == answer[j]) {
					blow++;
				}
			}
		}
		return blow;
	}

	// ����
	public static void result(int hit, int blow, int[] answer, int count) {
		if (hit == answer.length && count <= 10) {
			System.out.println();
			System.out.println("����!! ");
			System.out.println("��������܂ł�" + count + "�񂩂���܂���");
			System.out.println(" ");

		} else if (count <= 10) {
			System.out.println();
			System.out.println("HIT " + hit + "�ABLOW " + (blow - hit) + "��");
		}
	}
}