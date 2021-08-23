package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj14712_�۸�۸� {

	static int[][] map;
	static int N, M;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp[] = br.readLine().split(" ");

		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		
		map = new int[N+1][M+1]; // 1-index
		
		dfs((N*M) -1);	//15
		System.out.println(count);
	}

	/* 1. �� ĭ ���� ��ȸ
	 * 2. �� ĭ ������ �� �� ĭ�� �׸� (���� ��, ������)
	 * 3. ���� ĭ�� �׸� ���Ҵµ� �ֺ��� 3���� ���� �� �׳� �н� -> �� �δ� ���
	 * 4. �׸� ���� ���� -> ��� ĭ ��ȸ �� �� (count++)
	 */
	
	public static void dfs(int cnt) {
		if(cnt == -1  ) {
			count++;
			return;
		}
		
		int x = cnt / M;	//���� ������.
		int y = cnt %M;
		
//		x 1 0
//		x x 0
//		0 0 0
		
		System.out.println(x + " "+y);
		
		if(!(map[x][y+1]==1 && map[x+1][y]==1 && map[x+1][y+1]==1) ) {
			//����ĭ ���� x
			//���� ĭ ����
			map[x][y] = 1;
			dfs(cnt-1);
			
			//��ĳ ĭ ���� x
			map[x][y] = 0;
			}
		dfs(cnt-1);
						
		
	}

}
