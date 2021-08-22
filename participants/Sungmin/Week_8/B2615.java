import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class B2615 {
	static StringBuilder sb = new StringBuilder();
	static boolean visited[][];
	static int board[][];
	static ArrayList<Integer> count = new ArrayList<Integer>();
	public static void main(String[] args) {
		try {
			BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
			int vertex=19;
			visited = new boolean[vertex+1][vertex+1];
			board = new int[vertex+1][vertex+1];
			for(int i=1;i<vertex+1;i++) {
				String[] temp = input.readLine().split(" ");
				for(int i1=1;i1<temp.length+1;i1++) {
					board[i][i1]=Integer.parseInt(temp[i1-1]);
				}
			}//�ٵ��� �Է� �迭
			for(int i=1;i<=vertex;i++) {
				for(int i1=1;i1<=vertex;i1++) {
					if(board[i][i1]==1)
						dfs(i,i1,1,1,0,vertex);
					else if(board[i][i1]==2)
						dfs(i,i1,2,1,0,vertex);
				}
			}
			System.out.print("0");
			input.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void dfs(int x, int y, int blackwhite, int count, int direction, int size) {
		//blackwhite�� 1�̸� ������, 2�� �Ͼ��
		//direction 0:ó��, 1:������(y����), 2:�Ʒ�(x����), 3:�����ʾƷ��밢��, 4:���ʾƷ��밢��
		if(count==5) {
			if(direction==1) {
				if(y+1<=size&&board[x][y+1]==blackwhite) return;//���� ���� �˻�. ���� ������ ���ٸ� ����
					System.out.println(blackwhite);
					System.out.println(x+" "+(y-4));
					System.exit(0);
			}
			else if(direction==2) {
				if(x+1<=size&&board[x+1][y]==blackwhite) return;
					System.out.println(blackwhite);
					System.out.println((x-4)+" "+y);
					System.exit(0);
			}
			else if(direction==3) {
				if(x+1<=size&&y+1<=size&&board[x+1][y+1]==blackwhite) return;
					System.out.println(blackwhite);
					System.out.println((x-4)+" "+(y-4));
					System.exit(0);
			}
			else {
				if(x+1<=size&&y-1>=1&&board[x+1][y-1]==blackwhite) return;
				System.out.println(blackwhite);
				System.out.println(x+" "+y);
				System.exit(0);
			}
		}
		if(direction==0) {
			int dx[] = {0,1,1,1};
			int dy[] = {1,0,1,-1};
			for(int i=0;i<dx.length;i++) {
				int newX=x+dx[i];
				int newY=y+dy[i];
				if(newX>=1&&newX<=size&&newY>=1&&newY<=size&&board[newX][newY]==blackwhite) {
					if(i==0) dfs(newX, newY, blackwhite, count+1, 1, size);//������ Ž��
					else if(i==1) dfs(newX, newY, blackwhite, count+1, 2, size);//�Ʒ� Ž��
					else if(i==2) dfs(newX, newY, blackwhite, count+1, 3, size);//������ �밢
					else dfs(newX, newY, blackwhite, count+1, 4, size);//���� �Ʒ� �밢
				}	
			}
		}
		else if(direction==1) {//y�� ����
			if(count==2&&y-2>=1&&board[x][y-2]==blackwhite)return;//���� ���� �˻�.���� ������ ���� ���̶�� ����
			if(x>=1&&x<=size&&y+1>=1&&y+1<=size&&board[x][y+1]==blackwhite)
				dfs(x, y+1, blackwhite, count+1, 1, size);
		}
		else if(direction==2) {
			if(count==2&&x-2>=1&&board[x-2][y]==blackwhite)return;
			if(x+1>=1&&x+1<=size&&y>=1&&y<=size&&board[x+1][y]==blackwhite)
				dfs(x+1, y, blackwhite, count+1, 2, size);
		}
		else if(direction==3){
			if(count==2&&y-2>=1&&x-2>=1&&board[x-2][y-2]==blackwhite)return;
			if(x+1>=1&&x+1<=size&&y+1>=1&&y+1<=size&&board[x+1][y+1]==blackwhite)
				dfs(x+1, y+1, blackwhite, count+1, 3, size);
		}
		else {
			if(count==2&&y+2<=size&&x-2>=1&&board[x-2][y+2]==blackwhite)return;
			if(x+1>=1&&x+1<=size&&y-1>=1&&y-1<=size&&board[x+1][y-1]==blackwhite)
				dfs(x+1, y-1, blackwhite, count+1, 4, size);
		}
	}
}