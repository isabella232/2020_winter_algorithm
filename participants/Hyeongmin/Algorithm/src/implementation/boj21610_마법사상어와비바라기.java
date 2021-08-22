package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

class cloud{
	int x,y;
	
	cloud(int x, int y){
		this.x = x;
		this.y= y;
	}
}
public class boj21610_��������ͺ�ٶ�� {

	static int n, m;
	static int map[][];

	static int dx[] = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int dy[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	
	static Deque<cloud> list = new ArrayDeque<cloud>();
	static boolean visit[][]; //���� ���� ��ġ
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String temp[] = br.readLine().split(" ");

		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);

		map = new int[n][n];
		visit = new boolean[n][n];
		
		for(int i=0;i<n;i++) {
			temp = br.readLine().split(" ");
			for(int j=0;j<n;j++)
				map[i][j] = Integer.parseInt(temp[j]);
		}
		
		
		/*---------------map setting---------------------*/
		//������ ��ġ
		list.offer(new cloud(n-1, 0));
		list.offer(new cloud(n-1, 1));
		list.offer(new cloud(n-2, 0));
		list.offer(new cloud(n-2, 1));
		
		//�̵�
		int d = -1;	//����
		int s = -1; 	//�Ÿ�
		
		while(m-->0) {
			temp = br.readLine().split(" ");
			d = Integer.parseInt(temp[0]);
			s = Integer.parseInt(temp[1]);
			
			//���� �̵�
			move_cloud(d-1,s);
			//�N����
			copy_water();
			//���� ����
			new_cloud();
		}
		System.out.println(result());

	}
	static public int result() {
		int sum =0 ;
		
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				sum=sum+map[i][j];
		
		return sum;
	}
	//���� �̵�
	static public void move_cloud(int d, int s) {
		//���� ��ġ �̵�
		int size = list.size();
		while(size -- >0) {
			cloud now = list.poll();
			int mx = (dx[d]*s);	//���� ������ �ǳ�?
			int my = (dy[d]*s);
			
			if(mx>0)
				mx=mx%n;
			else if(mx<0) {
				mx = (-mx) %n;
				mx = -mx;
			}
			
			if(my>0)
				my=my%n;
			else if(my<0) {
				my = (-my) %n;
				my = -my;
			}
			
			
			int x = now.x+mx;
			int y = now.y+my;

			if(x<0)
				x = n+x;
			else if(x>=n)
				x= x-n;
			
			if(y<0)
				y = n+y;
			else if(y>=n)
				y=y-n;
			//�񳻸���
			map[x][y]+=1;
			list.offer(new cloud(x, y));
		}
	}
	
	//������
	static public void copy_water() {
		while(!list.isEmpty()) {
			cloud temp = list.pop();
			int x = temp.x;
			int y= temp.y;
			
			visit[x][y] = true;	//���� ��ġ ����
			
			if(x-1>=0 && y-1>=0 && map[x-1][y-1]!=0)
				map[x][y]+=1;
			if(x-1>=0 && y+1<n && map[x-1][y+1]!=0)
				map[x][y]+=1;
			if(x+1<n && y-1 >=0 && map[x+1][y-1]!=0)
				map[x][y]+=1;
			if(x+1<n && y+1 <n && map[x+1][y+1]!=0)
				map[x][y]+=1;
			
		}
	}
	
	static public void new_cloud() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(visit[i][j]) {
					visit[i][j] = false;
					continue;
				}
				if(map[i][j]>=2) {
					map[i][j]=map[i][j]-2;
					list.add(new cloud(i, j));
				}
			}
		}
	}
}
