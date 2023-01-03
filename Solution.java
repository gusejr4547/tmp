import java.util.Scanner;
import java.io.FileInputStream;

/*
   ����ϴ� Ŭ�������� Solution �̾�� �ϹǷ�, ������ Solution.java �� ����� ���� �����մϴ�.
   �̷��� ��Ȳ������ �����ϰ� java Solution ������� ���α׷��� �����غ� �� �ֽ��ϴ�.
 */
class Solution {

	public static void main(String args[]) throws Exception {
		/*
		 * �Ʒ��� �޼ҵ� ȣ���� ������ ǥ�� �Է�(Ű����) ��� input.txt ���Ϸκ��� �о���ڴٴ� �ǹ��� �ڵ��Դϴ�. �������� �ۼ��� �ڵ带
		 * �׽�Ʈ �� ��, ���Ǹ� ���ؼ� input.txt�� �Է��� ������ ��, �� �ڵ带 ���α׷��� ó�� �κп� �߰��ϸ� ���� �Է��� ������ ��
		 * ǥ�� �Է� ��� ���Ϸκ��� �Է��� �޾ƿ� �� �ֽ��ϴ�. ���� �׽�Ʈ�� ������ ������ �Ʒ� �ּ��� ����� �� �޼ҵ带 ����ϼŵ� �����ϴ�.
		 * ��, ä���� ���� �ڵ带 �����Ͻ� ������ �ݵ�� �� �޼ҵ带 ����ų� �ּ� ó�� �ϼž� �մϴ�.
		 */
		// System.setIn(new FileInputStream("res/input.txt"));

		/*
		 * ǥ���Է� System.in ���κ��� ��ĳ�ʸ� ����� �����͸� �о�ɴϴ�.
		 */
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		/*
		 * ���� ���� �׽�Ʈ ���̽��� �־����Ƿ�, ������ ó���մϴ�.
		 */

		for (int test_case = 1; test_case <= T; test_case++) {
			int result = 1;

			int[][] board = new int[9][9];
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					board[i][j] = sc.nextInt();
				}
			}

			int i = 0, j = 0;
			while (i < 9 && j < 9) {
				// 3x3 �Ǻ�
				if (i % 3 == 0 && j % 3 == 0) {
					boolean small_fail = false;
					int[] check = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
					for (int a = i; a < i + 3; a++) {
						for (int b = j; b < j + 3; b++) {
							check[board[a][b]-1]++;
						}
					}

					for (int idx = 0; idx < 9; idx++) {
						if (check[idx] != 1) {
							small_fail = true;
							break;
						}
					}
					if (small_fail) {
						result = 0;
						break;
					}
				}
				
				// 1x9, 9x1 �Ǻ�
				if (i==j) {
					
				}
				
				
				if (result==0) {
					break;
				}
				
				if (j == 9) {
					i++;
					j = 0;
				} else {
					j++;
				}
			}

			System.out.println("#t " + result);
		}
	}
}