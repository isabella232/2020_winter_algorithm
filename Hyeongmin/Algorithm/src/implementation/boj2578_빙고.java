package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class dot{
	int x;
	int y;
	dot(int x, int y){
		this.x =x;
		this.y=y;
	}
}
public class boj2578_���� {
	
	static int map[][] = new int[5][5];
	static boolean visit[][] = new boolean[5][5];
	
	static HashMap<Integer, dot> position = new HashMap<Integer, dot>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String temp[];
		
		//bingo
		for(int i=0;i<5;i++) {
			temp = br.readLine().split(" ");
			for(int j=0;j<5;j++) {
				map[i][j] = Integer.parseInt(temp[j]);
				dot p = new dot(i,j);
				
				position.put(Integer.parseInt(temp[j]), p);
			}
		}
		
		int count=0;
		//call number
		for(int i=0;i<5;i++) {
			temp = br.readLine().split(" ");
			for(String a : temp) {
				count++;
				int number = Integer.parseInt(a);
				
				//���� �����(�湮)
				dot n = position.get(number);
				visit[n.x][n.y] = true;
				
				//�� ���ڷκ��� �� �� �밢�� ���� Ȯ��
				
			}
		}
		
	}
	
	//���� Ȯ��
	public static boolean bingo(int x, int y) {
		//��
		for(int i=0;i<5;i++) {
			if(!visit[x][i])
				return false;
		}
		
		//��
		for(int i=0;i<5;i++) {
			if(!visit[i][y])
				return false;
		}
		
		//�밢��
		for(int)
		return true;
	}
}
