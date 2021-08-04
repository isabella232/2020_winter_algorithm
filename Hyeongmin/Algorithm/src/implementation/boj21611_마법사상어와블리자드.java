package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj21611_��������ͺ��ڵ� {
	static int n;
	static int m;
	static int map[][];
	
	//���� ����: �� �� �� ��
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int direction = 0;
		
	static int result;
	static int shark;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String temp[] = br.readLine().split(" ");
		
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		 
		map = new int[n][n];
		
		for(int i=0;i<n;i++) {
			temp = br.readLine().split(" ");
			for(int j=0;j<n;j++)
				map[i][j] = Integer.parseInt(temp[j]);
		}
		
		//���� ���� d(i)-���� s(i)-�Ÿ�
		for(int i=0;i<m;i++) {
			temp = br.readLine().split(" ");
			
			int d = Integer.parseInt(temp[0]);
			int s = Integer.parseInt(temp[1]);
			blizard(d, s);
		}
	}
	
	//d-1 ���� / s �Ÿ�
	//���ڵ�
	public static void blizard(int d, int s) {
		
		int x = (n+1)/2;
		int y = (n+1)/2;

		for(int i=0;i<s;i++) {
			x = x+dx[d-1];
			y = y+dy[d-1];
			
			map[x][y] = 0;
		}
	}
	
	//��ĭ ä���
	

}
