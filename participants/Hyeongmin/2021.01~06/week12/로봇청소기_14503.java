package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class �κ�û�ұ�_14503 {
	static int d = 0;
	static int map[][];
	static int n, m;
	static boolean visit[][];
	static int dx[] = { 0, 1, 0, -1 }; // �ϵ�����
	static int dy[] = { -1, 0, 1, 0 };
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String temp[] = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);

		map = new int[n][m];
		visit = new boolean[n][m];

		temp = br.readLine().split(" ");

		int r = Integer.parseInt(temp[0]);
		int c = Integer.parseInt(temp[1]);
		d = Integer.parseInt(temp[2]);

		for (int i = 0; i < n; i++) {
			temp = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}

		/*------------------input-------------*/

		clean(r, c);
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (visit[i][j])
					count++;
		System.out.println(count);

	}

	public static void clean(int x, int y) {
		visit[x][y] = true; // ������ġ û��

		int nextx = x;
		int nexty = y;

		for (int i = 0; i < 4; i++) {
			// �����̵� ��������
			if (d == 0)
				d = 3;
			else
				d--;

			// ������ ����
			nextx = x + dy[d];
			nexty = y + dx[d];

			if (nextx < 0 || nexty < 0 || nextx >= n || nexty >= m)
				continue;

			// �湮�����ʾҰ�, ���̾ƴϸ� �̵�
			if (!visit[nextx][nexty] && map[nextx][nexty] == 0) {
				clean(nextx, nexty);
				return;
			}

		}
		// �׹��� ��� û�� �Ǿ��ְų� ���ΰ�� ��ĭ ����

		for (int i = 0; i < 4; i++) {
			// �����̵� ��������
			if (d == 0)
				d = 3;
			else
				d--;

			// ����1ĭ
			if (i == 1) {
				nextx = x + dy[d];
				nexty = y + dx[d];
			}
		}

		// ���� ������ �湮�߰ų� ���̰ų� ���� �Ѿ��
		if (nextx < 0 || nexty < 0 || nextx >= n || nexty >= m)
			return;
		else if(map[nextx][nexty] == 1)
			return;
		else {
			clean(nextx, nexty);
			return;
		}

	}
}
