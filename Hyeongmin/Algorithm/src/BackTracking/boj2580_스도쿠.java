package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class boj2580_������ {

	static int map[][] = new int[9][9];
	static ArrayList[] visit = new ArrayList[9];	//positive number �湮

	static ArrayList[] Positive_Number = new ArrayList[9]; //�� �� �� �ִ� ��
	static ArrayList[] Zero_Position = new ArrayList[9]; //�ึ�� 0�� ��ġ

	static boolean flag = false;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<9;i++) {
			Positive_Number[i] = new ArrayList<Integer>();
			Zero_Position[i] = new ArrayList<Integer>();
			visit[i] = new ArrayList<Integer>();

			for(int j=1;j<=9;j++) 
				Positive_Number[i].add(j);
			
		}
		
		String temp[];
		
		for(int i=0;i<9;i++) {
			temp = br.readLine().split(" ");
			
			//j:��
			for(int j=0;j<9;j++) {
				int number = Integer.parseInt(temp[j]);
				map[i][j]  = number;
				
				//zero_position[��] = {�ึ�� ��� �� �� �ִ� ��}
				if(number != 0) {
					int index = Positive_Number[j].indexOf(number);
					Positive_Number[j].remove(index);
				}
				else
					Zero_Position[j].add(i);	//0�� ��ġ (j,i)
				
			}
		}
		for(int i=0;i<9;i++) {
			for(int j=0;j<Positive_Number[i].size();j++)
				visit[i].add(Positive_Number[i].get(j));
		}
		/*-------------------input Success -----------------------*/
		
		
		//���� ä���
		//type ( ��, zero ��ġ)
		dfs(0,0);

		
	}
	
	//idx : ��
	public static void dfs (int idx, int x_index) {
		//���������� ���� �߰ų�, �׷� ��찡 ���� ��
		if( idx==9) {
			for(int i=0;i<9;i++) {
				for(int j=0;j<8;j++)
					System.out.print(map[i][j]+" ");
				System.out.println(map[i][8]);
			}
			
			System.exit(0);//���� ����
		}
		
		if(Zero_Position[idx].size()==0)
			dfs(idx+1,0);
		
		//idx ��
		//0�� ��ġ ( , idx)
		
		//�� �� �� ���ڸ� 
		int x = (int) Zero_Position[idx].get(x_index);
		for(int i=1;i<10;i++) {
			map[x][idx] = i;
			
			if(check(x, idx)) {
				if(x_index == Zero_Position[idx].size()-1)
					dfs(idx+1,0);
				else
					dfs(idx,x_index+1);
			}
			map[x][idx] = 0;
		}
	}
	
	//�� �ߺ�, 3*3 �ߺ� Ȯ��
	public static boolean check(int x, int y) {
		
		//�� �ߺ� üũ
		for(int i=0;i<9;i++) {
			if(y==i)
				continue;
			int a = map[x][y];
			int b = map[x][i];
			if(a==b)
				return false;
		}
		
		//�� �ߺ� üũ
		for(int i=0;i<9;i++) {
			if(x==i)
				continue;
			int a = map[x][y];
			int b = map[i][y];
			if(a==b)
				return false;
		}
		
		int row = (x/3) *3;
		int column = (y/3)*3;
		//3*3 �ߺ� Ȯ��
		for(int i=row;i<row+3;i++) {
			for(int j = column;j<column+3;j++) {
				int a = map[x][y];
				int b = map[i][j];
				
				if(x==i && y==j)
					continue;
				else if(a ==b)
					return false;
				
			}
		}
		return true;
	}
}
