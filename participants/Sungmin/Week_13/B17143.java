import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B17143 {
	static int result=0;
	static class Shark{
		int X;
		int Y;
		int direction;
		//1�� ��, 2�� �Ʒ�, 3�� ������, 4�� ����
		int speed;
		int size;
		boolean catched;
		Shark(int X, int Y, int speed, int direction, int size){
			this.X=X;
			this.Y=Y;
			this.direction=direction;
			this.speed=speed;
			this.size=size;
			this.catched=false;
		}
	}
	
	static void sharkMove(Shark[] S, int r, int c) {
		for(int i=1;i<=S.length-1;i++) {
			if(S[i].catched)continue;
			int count=S[i].speed;
			int[] dx= {-1,1,0,0};
			int[] dy= {0,0,1,-1};
			while(count!=0) {
				int newX=S[i].X+dx[S[i].direction-1];
				int newY=S[i].Y+dy[S[i].direction-1];
				if(newX>=1&&newX<=r&&newY>=1&&newY<=c) {
					//��ȿ�� �˻�
					S[i].X=newX;
					S[i].Y=newY;
				}
				else {
					//��ġ�� �ٲ� ȸ��
					if(S[i].direction==1) S[i].direction=2;
					else if(S[i].direction==2) S[i].direction=1;
					else if(S[i].direction==3) S[i].direction=4;
					else S[i].direction=3;
					count++;
				}
				count--;
			}
		}
	}
	
	static void sharkArr(Shark[] S, int[][] board) {
		for(int i=1;i<=S.length-1;i++) {
			if(S[i].catched)continue;
			if(board[S[i].X][S[i].Y]!=0) {
				//�ٸ� �� ���� ���
				if(S[i].size>S[board[S[i].X][S[i].Y]].size) {
					//�� �� �� ŭ
					S[board[S[i].X][S[i].Y]].catched=true;
					board[S[i].X][S[i].Y]=i;
				}
				else {
					//�� �� �� ����
					S[i].catched=true;
				}
			}
			else {
				//���� ���
				board[S[i].X][S[i].Y]=i;
			}
		}
	}
	
	static void fishing(int location, int[][] board, int r, Shark[] S) {
		for(int i=1;i<=r;i++) {
			if(board[i][location]!=0&&!S[board[i][location]].catched) {
				S[board[i][location]].catched=true;
				result+=S[board[i][location]].size;
				break;
			}
		}
	}
	
	static void boardReset(int[][] board, Shark[] S) {
		for(int i=1;i<=S.length-1;i++)
			board[S[i].X][S[i].Y]=0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		String[] s=input.readLine().split(" ");
		int x=Integer.parseInt(s[0]);
		int y=Integer.parseInt(s[1]);
		int m=Integer.parseInt(s[2]);
		Shark[] sharks=new Shark[m+1];
		//��ũ �迭���� 1���� �ִ´�.
		int[][] board=new int[x+1][y+1];
		for(int i=1;i<=m;i++) {
			s=input.readLine().split(" ");
			int r=Integer.parseInt(s[0]);
			int c=Integer.parseInt(s[1]);
			int speed=Integer.parseInt(s[2]);
			int d=Integer.parseInt(s[3]);
			int z=Integer.parseInt(s[4]);
			sharks[i]=new Shark(r,c,speed,d,z);
			board[sharks[i].X][sharks[i].Y]=i;
		}
		
		int count=0;
		while(count<y) {
			count++;
			fishing(count, board, x, sharks);
			boardReset(board, sharks);
			sharkMove(sharks, x, y);
			sharkArr(sharks, board);
		}
		System.out.print(result);
		input.close();
	}
}