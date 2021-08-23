package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class xy implements Comparable<xy>{
	int x;
	int y;

	xy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(xy o) {
		if(this.y >o.y)
			return 1; //�� ��������
		else if(this.y == o.y) {
			if(this.x< o.x) //�� ��������
				return 1;
		}
			
		return -1;
	}
}

public class �̳׶�_2933 {

	static int r, c;
	static String map[][];
	static boolean visit[][];

	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String temp[] = br.readLine().split(" ");

		r = Integer.parseInt(temp[0]);
		c = Integer.parseInt(temp[1]);

		map = new String[r][c];
		visit = new boolean[r][c];

		for (int i = 0; i < r; i++) {
			temp = br.readLine().split("");
			for (int j = 0; j < c; j++) {
				map[i][j] = temp[j];
			}
		}

		/*---------------intput-------*/

		int n = Integer.parseInt(br.readLine());

		temp = br.readLine().split(" "); // ������ ����

		// ���� ���� ������ ���鼭 ������
		for (int i = 0; i < n; i++) {
			int shot = r-Integer.parseInt(temp[i]);

			String check = String.valueOf('x');

			boolean flag = false;
			// ����
			if (i % 2 == 0) {
				for (int j = 0; j < c; j++) {
					// �̳׶� ����
					if (map[shot][j].contains(check)) {
						map[shot][j] = String.valueOf('.');
						flag = true;
						break;
					}
				}
			}

			// ������
			else {
				for (int j = c - 1; j >= 0; j--) {
					// �̳׶� ����
					if (map[shot][j].contains(check)) {
						map[shot][j] = String.valueOf('.');
						flag = true;
						break;
					}
				}
			}

			/*--------���� �� �ѽŰ� ������ �۸����� Ȯ��--------*/
			if (flag) {
				GlisterXCheck();
				visit = new boolean[r][c];
			}

		}
		
		/*-------������-----------*/
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				if(j==c-1)
					System.out.println(map[i][j]);
				else
					System.out.print(map[i][j]+" ");
			}
		}
	}

	// �ٴڿ� �پ� �۸����Ϳ��� ������ �۸����� �Ǵ�
	static void GlisterXCheck() {
		
		//�ٴڿ� �پ��ִ� �۸����� �湮ó��
		for (int i = 0; i < c; i++) 
			if (map[r - 1][i].contains(String.valueOf('x')) && !visit[r - 1][i]) 
				Glister(r - 1, i);
		
		ArrayList<xy> list = new ArrayList<xy>();
		
		//�ٴڿ��� ������ �� üũ
		for(int i=0;i<r;i++) {

			for(int j=0;j<c;j++) {
				//�߰�
				if(map[i][j].contains(String.valueOf('x')) && !visit[i][j]) {
					// list(x,y) : �ѵ�� ���� 
					aboveGlister(i,j,list);
					
					//���� ����(���� ���� �Ʒ��� �ٴ�)
					Collections.sort(list);
					
					//�ٴ����� �󸶳� ��������
					int down = getdown(list);
					
					//�� ����
					if(down!=0) {
						DrawDown(list, down);
						//�ϳ� ����Ʈ���� break;
						return;
					}
					

				}
				
				
			}
		}
	}
	//���� �������� �Լ�
	static void DrawDown(ArrayList<xy> temp, int down) {
		for(xy e : temp) {
			int nextx = e.x+down;
			int nexty = e.y;
			
			map[e.x][e.y] = String.valueOf('.');
			map[nextx][nexty] = String.valueOf('x');	
			
			visit[e.x][e.y] = false;
			visit[nextx][nexty] = true;
		}
	}
	
	static int getdown(ArrayList<xy> temp) {
		//temp : ��(y)������ , ��(x) �������� ����
		
		int starty = temp.get(0).y;
		int down_min = Integer.MAX_VALUE;
		
		for(int i=0;i<temp.size();i++) {
			xy e = temp.get(i);
			if(i==0) {
				for(int j=e.x+1;j<r;j++) {
					//�̳׶� ����
					if(map[j][e.y].contains(String.valueOf('x'))) {
						down_min = Math.min(down_min, (j-1)-e.x);
						break;
					}
					//�ٴ�
					else if(j==r-1) {
						down_min = Math.min(down_min, j-e.x);
						break;
					}
				}
			}
			else {
				// �ٴڸ鸸 üũ
				if(temp.get(i).y!=starty) {
					starty = temp.get(i).y;
					
					for(int j=e.x+1;j<r;j++) {
						//�̳׶� ����
						if(map[j][e.y].contains(String.valueOf('x'))) {
							down_min = Math.min(down_min, (j-1)-e.x);
							break;
						}
						//�ٴ�
						else if(j==r-1) {
							down_min = Math.min(down_min, j-e.x);
							break;
						}
					}
				}
			}
		}
		
		return down_min;
	}

	static void Glister(int dr, int dc) {
		ArrayDeque<xy> list = new ArrayDeque<xy>();
		
		if(!visit[dr][dc]) 
			visit[dr][dc] =true;
		
		
		for (int i = 0; i < 4; i++) {
			int nextx = dr + dx[i];
			int nexty = dc + dy[i];

			if (nextx < 0 || nexty < 0 || nextx > r - 1 || nexty > c - 1)
				continue;
			//���� �湮�� �� ����, x�ΰ� ----> T
			else if (!visit[nextx][nexty] && map[nextx][nexty].contains(String.valueOf('x'))) {
				xy e = new xy(nextx, nexty);
				list.add(e);
				visit[nextx][nexty] = true;
			}
		}
		
		while(!list.isEmpty()) {
			xy e = list.poll();
			Glister(e.x, e.y);
		}
	}

	// ���߿� �ִ� �۸����� ��ǥ üũ
	static void aboveGlister(int x, int y, ArrayList<xy> temp) {
		ArrayDeque<xy> list = new ArrayDeque<xy>();

		if(!visit[x][y]) {
			xy e = new xy(x,y);
			temp.add(e);
			visit[x][y] =true;
		}
		
		for (int i = 0; i < 4; i++) {
			int nextx = x + dx[i];
			int nexty = y + dy[i];

			if (nextx < 0 || nexty < 0 || nextx > r - 1 || nexty > c - 1)
				continue;
			//���� �湮�� �� ����, x�ΰ� ----> T (���ִ°� �߿�)
			else if (!visit[nextx][nexty] && map[nextx][nexty].contains(String.valueOf('x'))) {
				xy e = new xy(nextx, nexty);
				list.add(e);
				visit[nextx][nexty] = true;
				temp.add(e);
			}
		}
		
		while(!list.isEmpty()) {
			xy e = list.poll();
			aboveGlister(e.x, e.y,temp);
		}
		
	}

}
