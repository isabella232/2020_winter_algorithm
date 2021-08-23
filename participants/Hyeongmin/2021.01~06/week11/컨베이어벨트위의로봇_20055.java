package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class �����̾Ʈ���Ƿκ�_20055 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String temp[] = br.readLine().split(" ");
		int count = 0;
		int stage = 0;
		int n = Integer.parseInt(temp[0]);
		int k = Integer.parseInt(temp[1]);

		temp = br.readLine().split(" ");

		int belt[] = new int[2 * n];
		boolean visit[] = new boolean[n];

		for (int i = 0; i < belt.length; i++)
			belt[i] = Integer.parseInt(temp[i]);

		/* ------------�Էº� ---------------- */

		while (true) {
			stage++; // �ܰ� +1

			// 1�ܰ� ��Ʈ�̵�
			int last_belt = belt[(2 * n) - 1];

			for (int i = (2 * n) - 1; i > 0; i--) {
				belt[i] = belt[i - 1];
			}
			belt[0] = last_belt;
			
			// 1�ܰ� ��Ʈ�̵��� ���� �����ִ� �κ� �̵�
			for (int i = n - 1; i > 0; i--) {
				visit[i] = visit[i - 1];
			}
			// ùĭ
			visit[0] = false;

			visit[n - 1] = false;

			// 2�ܰ� �κ� �̵�
			for (int i = n - 1; i > 0; i--) {
				if (belt[i] > 0 && visit[i - 1] && !visit[i]) {
					visit[i] = true;
					visit[i - 1] = false;
					belt[i] -= 1;
				}
			}

			visit[n-1] = false;


			// 3�ܰ� �κ� �ø���
			if (!visit[0] && belt[0] > 0) {
				visit[0] = true;
				belt[0] -= 1;
			}

			int cc = 0;
			for (int a : belt) {
				if (a == 0)
					cc++;
			}
			if (cc >= k)
				break;
		}

		System.out.println(stage);
	}

}
