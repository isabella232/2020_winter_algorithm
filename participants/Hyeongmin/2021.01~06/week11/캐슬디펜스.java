package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class ĳ�����潺 {
	static int map[][];
	static int copymap[][];
	static int count = 0;
	static int result=0;
	static int arr[];
	static int n, m, d;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String temp[] = br.readLine().split(" ");

		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		d = Integer.parseInt(temp[2]);

		map = new int[n][m];
		copymap = new int[n][m];

		arr = new int[3]; // �ü� ��ġ

		for (int i = 0; i < n; i++) {
			temp = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
				copymap[i][j] = Integer.parseInt(temp[j]);

			}
		}

		/*----------------�Էº� ------------------*/

		// �ü� ��ġ
		combination(0, m, 3, 0);
		
		System.out.println(result);

	}

	public static void combination(int index, int n, int r, int target) {
		if (r == 0) {
			// �ü���ġ x��ǥ Ȯ��
			count=0;
			init_map(map);
			start_game(arr, map);
			result = Math.max(result, count);
			
		} else if (target == n)
			return;
		else {
			arr[index] = target;
			combination(index + 1, n, r - 1, target + 1);
			combination(index, n, r, target + 1);
		}
	}

	public static void start_game(int arr[], int map[][]) {

		for (int tile = 0; tile < n; tile++) {
			// �ü� ��ġ
			boolean visit[][] = new boolean[n][m];
			int x = 0, y = 0;

			for (int k = 0; k < 3; k++) {
				int archer = arr[k]; // �ü� x��ǥ
				int distance = Integer.MAX_VALUE; // ������ �ּҰŸ�
				
				boolean check =false;
				// �ؿ��� ���� �Ųٷ�
				for (int i = n; i > 0; i--) {
					for (int j = 0; j < m; j++) {
						if (map[i - 1][j] == 1) {
							// ������ �ּҰŸ�
							int temp_D = Distance(j, i-1, archer);
							if (temp_D <= d && distance > temp_D) {
								distance = temp_D;
								x = j;
								y = i-1;
								check= true;
							}
							//�ּҰŸ� ������� �� �����̸� �̰ɷ�
							else if(temp_D <= d && distance==temp_D) {
								if(x>j) {
									x = j;
									y = i-1;
									check= true;
								}
							}
						
						}
					}
				}
				// ���� ó������ �ʾҴٸ�(�ߺ�x)
				if(check) {
					if (!visit[y][x]) {
	//					count++;
	//					map[x][y] = 0;	//óġ���� ���ֱ�
						visit[y][x] = true;
					}
				}
			}
			// ������� ���� ����
			
			for (int i = n; i > 0; i--) {
				for (int j = 0; j < m; j++) {
					if(visit[i-1][j]) {
						count++;
						map[i-1][j] = 0;
					}
				}
			}
			// tile_move
			tile_move(map);

		}
	}

	// �� �ʱ�ȭ
	public static void init_map(int map[][]) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				map[i][j] = copymap[i][j];
		}
	}

	public static void tile_move(int map[][]) {
		for (int i = n - 1; i >= 0; i--) {
			if (i == 0) {
				Arrays.fill(map[i], 0);
			} else {
				for (int j = 0; j < m; j++) {
					map[i][j] = map[i - 1][j];
				}
			}
		}
	}

	public static int Distance(int x, int y, int archerx) {
		return Math.abs(x - archerx) + Math.abs(n - y);
	}

}
