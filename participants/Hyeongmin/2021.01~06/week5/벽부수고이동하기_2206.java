package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

class point {
	int x;
	int y;
	int count;
	boolean boom;

	point() {
	};

	point(int x, int y, int count, boolean boom) {
		this.x = x;
		this.y = y;
		this.count = count;
		this.boom = boom;
	}
}

public class ���μ����̵��ϱ�_2206 {

	static int map[][];
	static int visit[][][];
	static ArrayDeque<point> list = new ArrayDeque<point>();

	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int n;
	static int m;
	static ArrayList<Integer> result = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String temp[] = br.readLine().split(" ");

		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);

		map = new int[n][m];
		visit = new int[n][m][2];

		for (int i = 0; i < n; i++) {
			temp = br.readLine().split("");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}

		point start = new point(0, 0, 0, false);

		list.offer(start);

		bfs();
		if (result.isEmpty())
			System.out.println(-1);
		else {
			Collections.sort(result);
			System.out.println(result.get(0));

		}
	}

	public static void bfs() {
		while (!list.isEmpty()) {
			point temp = list.poll();

			if (temp.x == m - 1 && temp.y == n - 1) {
				result.add(temp.count + 1);
				if(temp.boom)
					visit[temp.y][temp.x][1] = temp.count + 1;
				else
					visit[temp.y][temp.x][0] = temp.count + 1;

				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nextx = temp.x + dx[i];
				int nexty = temp.y + dy[i];

				// ���� ����� ���
				if (nextx < 0 || nexty < 0 || nextx >= m || nexty >= n)
					continue;

				// ���� �μ��� �ʾ�����
				if (!temp.boom) {
					// ó���湮
					if (visit[nexty][nextx][0] == 0) {
						// ���� ������ �ʾ�����
						if (map[nexty][nextx] == 0) {
							point new_point = new point(nextx, nexty, temp.count + 1, false);
							list.offer(new_point);
							visit[nexty][nextx][0] = temp.count + 1;
						}
						// ���� ������ ��
						else {
							point new_point = new point(nextx, nexty, temp.count + 1, true);
							list.offer(new_point);
							visit[nexty][nextx][1] = temp.count + 1;

						}
					}
					/*---------------------����-----------------------------*/

//					// �湮�� �������� ���� �Ⱥνð� ������ �� ���� �� ���� ��(count = ��� ����)
//					else if (visit[nexty][nextx] != 0 && visit[nextx][nexty] >= temp.count + 1) {
//						if (map[nexty][nextx] == 0) {
//							point new_point = new point(nextx, nexty, temp.count + 1, false);
//							list.offer(new_point);
//							visit[nexty][nextx] = temp.count + 1;
//						}
//						// ���� ������ ��
//						else {
//							point new_point = new point(nextx, nexty, temp.count + 1, true);
//							list.offer(new_point);
//							visit[nexty][nextx] = temp.count + 1;
//
//						}
//					}
				}

				/*--------------���� �μ� ����-------------------------*/
				else if (temp.boom) {
					// ������ǥ�� �湮�߰ų� 1�϶� �н�
					if (visit[nexty][nextx][1] != 0 || map[nexty][nextx] == 1)
						continue;
					//�湮�� �������� ���� �μ��� ������ 
//					else if (visit[nexty][nextx] != 0 && visit[nextx][nexty] >= temp.count + 1) {
//						if (map[nexty][nextx] == 0) {
//							point new_point = new point(nextx, nexty, temp.count + 1, true);
//							list.offer(new_point);
//							visit[nexty][nextx] = temp.count + 1;
//						}
//						
//					} 
					
					else {
						point new_point = new point(nextx, nexty, temp.count + 1, true);
						list.offer(new_point);
						visit[nexty][nextx][1] = temp.count + 1;

					}
				}
			}
		}
	}

}
