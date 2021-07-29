package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class xy {
	int x;
	int y;
	xy(int x, int y){
		this.x = x;
		this.y =y;
	}
	
}
class SeatScore implements Comparable<SeatScore>{
	int x;	// ��
	int y;	// ��
	int like; //����
	int nulls; // ����
	SeatScore(int like, int nulls, int x, int y){
		this.like = like;
		this.nulls = nulls;
		this.x = x;
		this.y =y;
	}
	@Override
	public int compareTo(SeatScore o) {
		if(this.like < o.like)
			return 1;
		else if(this.like==o.like) {
			if(this.nulls < o.nulls)
				return 1;
			else if(this.nulls == o.nulls) {
				if(this.x > o.x)
					return 1;
				else if(this.x==o.x)
					if(this.y > o.y)
						return 1;
			}
		}
		
		return -1;
	}
	
}

public class boj21608_����ʵ��б� {
	static int n;
	static int map[][];
	static int student[][];	// (�л���ȣ) : �����ϴ� �л� ��
	static int SatisfyScore[] = {0,1,10,100,1000};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n+1][n+1];
		student = new int[(n*n)][5]; //�� ���� ù��° �ε��� : �л� ����
		
		String temp[];
		
		for(int i=0;i<n*n;i++) {
			temp = br.readLine().split(" ");
			for(int j=0;j<5;j++)
				student[i][j] = Integer.parseInt(temp[j]);
		}

		//�л� �ڸ� ��ġ
		for(int i=0;i<n*n;i++) {
			batch(student[i][0], student[i]);	//�л���ȣ(student), �����ϴ� �ֵ�
		}
		
		//�ڸ� ��ġ Ȯ��
//		for(int i=1;i<=n;i++) {
//			for(int j=1;j<n;j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println(map[i][n]);
//		}
		//������ ����
		System.out.println(SaScore());
	}
	
	public static int SaScore() {
		int sscore = 0;
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				int number = map[i][j];
				
				for(int x=0;x<n*n;x++) {
					if(number == student[x][0]) {
						int index = likeNear(i, j,student[x])[0];
						sscore += SatisfyScore[index];
						continue;
					}
				}
			}
		}
		
		return sscore;
	}
	
	//�л� �ڸ� ��ġ
	/* �켱����
	 * 1. �����ϴ� �л� ��
	 * 2. ����ִ� ĭ�� ���� ��
	 * 3. ���ȣ ������
	 * 4. ����ȣ ������
	 */
	public static void batch(int number, int likelist[]) {
		ArrayList<SeatScore> seatlist = new ArrayList<SeatScore>();
		//�����ϴ� �л� ��ó �ڸ� ã��
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<n+1;j++) {
				if(map[i][j]!=0)
					continue;
				int result[] = likeNear(i, j, likelist);
				
				seatlist.add(new SeatScore(result[0],result[1],i,j));
			}
		}
		
		Collections.sort(seatlist);
		
		map[seatlist.get(0).x][seatlist.get(0).y] = number;
		
	}
	
	//�����ϴ� �л� ã��(x,y, ��ó�� �����ϴ� ��� �p�� �ִ���)
	//return {�����ϴ� ��� �� , ���ڸ�}
	public static int[] likeNear(int x, int y, int likelist[]) {
		
		int count = 0;
		int nullseat = 0;
		
		for(int i =x-1 ; i<=x+1; i++) {
			for(int j=y-1; j<=y+1;j++) {				
				if(i<=0 || i>n || j<=0 || j>n || (x==i&&y==j))
					continue;
				
				if(map[i][j]==0&& (Math.abs(x-i)+Math.abs(y-j))==1) {
					nullseat++;
					continue;
				}
				
				for(int idx =1;idx<5;idx++) {
					if(map[i][j] == likelist[idx] && (Math.abs(x-i)+Math.abs(y-j))==1)
						count++;
				}
			}
		}
		
		int result[] = {count,nullseat};
		return result;
	}
	
}	


