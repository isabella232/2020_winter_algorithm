package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj9663_NQueen {
	static int n;
	static int map[][];
	static boolean visit[][];
	static int result =0;
	
	static int queen[] = new int[16];	//queen[��] = ��

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		visit = new boolean[n][n];
		
		dfs(0,0);
		
//		Arrays.fill(queen, -1);
		
//		batch(0);
		System.out.println(result);
	}
	
	//idx : ��
	public static void batch(int idx) {
		if(idx == n-1) {
			result++; 
			return;
		}
		//���� ��ġ
		for(int i=0;i<n;i++) {
			//�ش� ���� �湮���� �ʾҴٸ�
			if(queen[i] == -1) {
				queen[i] = idx;
				
				if(check(idx, i))	//x,y
					batch(idx+1);
				
				queen[i] = -1;
			}
		}	
//		batch(idx+1);
	}
	
	//���� �ߺ����� ����
	//��, �밢�� �� �����ϴ��� üũ
	//type(x,y) : �� ��
	public static boolean check(int x, int y) {
		//���� ������ Ȯ��
		for(int i=0;i<y;i++) {
			
			//�� �� �߿� ���� ��ġ�� ���ų� �밢���� ���̰� ���� ��
			if(queen[i]==queen[y] || Math.abs(i-y) == Math.abs(queen[i] - queen[y]) ) 
				return false;
		}
		
		return true;
	}
	
	
	public static void dfs (int cnt, int q) {
		if(cnt == n*n) {
			if(q==n)
				result++;
			return;
		}
		if(q==n) {
			result++;
			return;
		}
		
		
		int x = cnt/n;
		int y = cnt%n;
		
		
		if(!visit[x][y]) {
			if(checking(x,y)) {
//				InVisit(x,y);
				visit[x][y] = true;
				dfs(cnt+1, q+1);
//				OutVisit(x, y);
			}
		}
		dfs(cnt+1,q);
	}
	public static boolean checking(int x, int y) {
		//�� �� �밢�� Ȯ��
		//��
		for(int i=0;i<n;i++) {
			if(visit[i][y]==true || visit[x][i]==true )
				return false;
			if(x-i>=0 && y+i<n && visit[x-i][y+i]==true)
				return false;
			if(x+i<n && y-i>=0 && visit[x+i][y-i] == true)
				return false;
		}
		
		return true;
	}
	public static void InVisit(int x, int y) {
		for(int i=0;i<n;i++) {
			visit[i][y] = true;
			visit[x][i] = true;
			
			if(x-i>=0 && y-i >=0) 
				visit[x-i][y-i] = true;
			
			if(x+i<n && y+i<n)
				visit[x+i][y+i] = true;
			
			if(x-i>=0 && y+i<n)
				visit[x-i][y+i] = true;
			if(x+i<n && y-i>=0)
				visit[x+i][y-i] = true;

		}
	}

	public static void OutVisit(int x, int y) {
		for(int i=0;i<n;i++) {
			visit[i][y] = false;
			visit[x][i] = false;
			
			if(x-i>=0 && y-i >=0) 
				visit[x-i][y-i] = false;
			
			if(x+i<n && y+i<n)
				visit[x+i][y+i] = false;
			if(x-i>=0 && y+i<n)
				visit[x-i][y+i] = false;
			if(x+i<n && y-i>=0)
				visit[x+i][y-i] = false;
		}
	}

}
