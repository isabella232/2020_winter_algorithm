package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ���� {
	static int map[][] = new int[20][20];
	static boolean visit[][] = new boolean[20][20];
	static int count = 0;

	static int dx[] = { 1, 0, -1, 0, 1, 1, -1, -1 };
	static int dy[] = { 0, 1, 0, -1, 1, -1, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=1;i<20;i++) {
			String temp[] = br.readLine().split(" ");
			
			for(int j=1;j<20;j++) {
				map[i][j] = Integer.parseInt(temp[j-1]);
			}
		}
		/* -----------------�Էº� ------------------------------- */
		
		for(int i=1;i<20;i++) {
			for(int j=1;j<20;j++) {
				//0�� �ƴϰų� �湮���� �ʾ��� ��
				if(map[i][j]!=0 ) {
					//�̵�����
					Stack<Integer> mc = move_check(i,j);
					
					while(!mc.isEmpty()) {
						int index = mc.pop();
						dfs(i,j,index);
						dfs(i,j,reverse_move(index));
						count--;
						if(count==5) {
							if(index==5)
								System.out.println(map[i][j]+"\n"+(i+4)+" "+(j-4));
							else
								System.out.println(map[i][j]+"\n"+i+" "+j);
							return;
						}
						count=0;
					}
				}
			}
			
		}
		
		System.out.println(0);

	}
	
	static public int reverse_move(int index) {
		
		int reverse_index = -1;
		
		switch (index) {
		case 0:
			reverse_index = 2;
			break;
		case 1:
			reverse_index = 3;
			break;
		case 2:
			reverse_index = 0;
			break;
			
		case 3:
			reverse_index = 1;
			break;
			
		case 4:
			reverse_index = 6;
			break;
			
		case 5:
			reverse_index = 7;
			break;
			
		case 6:
			reverse_index = 4;
			break;
			
		case 7:
			reverse_index = 5;
			break;
		default:
			break;
		}
		
		return reverse_index;
	}
	
	static public Stack<Integer> move_check(int x, int y) {
		Stack<Integer> result = new Stack<Integer>();
		
		int color = map[x][y];
		
		for(int i=0;i<8;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(nx>19 || ny >19 || nx<1 || ny<1)
				continue;
			else {
				if(map[nx][ny]==color)
					result.add(i);
			}
		}
		
		return result;
	}
	
	static public void dfs(int x, int y, int move_index) {
//		if(visit[x][y])
//			return;
		
//		visit[x][y] = true;
		count++; //0���� ����
		
		int color = map[x][y];

		
		int nx = x+dx[move_index];
		int ny = y+dy[move_index];
		
		if(nx>19 || ny >19 || nx<1 || ny<1)
			return;
		else if(map[nx][ny]==color) {
			dfs(nx,ny,move_index);
		}
		
		return;
	}
}
