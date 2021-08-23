import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class B19238 {
	static StringBuilder sb = new StringBuilder();
	static boolean visited[][];
	static int engineVisited[][];
	static int find=0;
	static int personCount=0;
	static int boardLevel[][];
	
	static class Person implements Comparable<Person>{
		int startX;
		int startY;
		int arriveX;
		int arriveY;
		boolean check;
		Person(int startX, int startY, int arriveX, int arriveY){
			this.startX=startX;
			this.startY=startY;
			this.arriveX=arriveX;
			this.arriveY=arriveY;
			this.check=false;
		}
		
		@Override
		public int compareTo(Person Word) {
			if(this.startX>Word.startX)return 1;
			else if(this.startX==Word.startX&&this.startY>Word.startY)return 1;
			return -1;
		}
	}
	
	static void visitedClear(int N) {
		for(int i=1;i<=N;i++) {
			for(int i1=1;i1<=N;i1++) {
				visited[i][i1]=false;
				boardLevel[i][i1]=0;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		String[] s=input.readLine().split(" ");
		int N=Integer.parseInt(s[0]);
		int M=Integer.parseInt(s[1]);
		int remainEngine=Integer.parseInt(s[2]);
		Person[] passengers=new Person[M+1];
		int[][] board=new int[N+1][N+1];
		visited=new boolean[N+1][N+1];
		engineVisited=new int[N+1][N+1];
		boardLevel=new int[N+1][N+1];
		Queue<Integer> qx = new LinkedList<Integer>();
		Queue<Integer> qy = new LinkedList<Integer>();
		ArrayList<Person> personQueue = new ArrayList<Person>();
		for(int i=1;i<=N;i++){
			s=input.readLine().split(" ");
			for(int i1=1;i1<=N;i1++) {
				board[i][i1]=Integer.parseInt(s[i1-1]);
				if(board[i][i1]==1)board[i][i1]=-1;
			}//1�� ���� �ƴ϶� -1�� ������ ����. 1�� ��� �ε����� �Ұ���
		}
		s=input.readLine().split(" ");
		int taxiStartX=Integer.parseInt(s[0]);
		int taxiStartY=Integer.parseInt(s[1]);
		engineVisited[taxiStartX][taxiStartY]=remainEngine;
		boardLevel[taxiStartX][taxiStartY]=1;
		qx.add(taxiStartX);
		qy.add(taxiStartY);
		
		for(int i=1;i<=M;i++) {//��� ���� ���
			s=input.readLine().split(" ");
			passengers[i]=new Person(Integer.parseInt(s[0]),Integer.parseInt(s[1]),
					Integer.parseInt(s[2]),Integer.parseInt(s[3]));
			board[Integer.parseInt(s[0])][Integer.parseInt(s[1])]=i;
		}
		
		int[] dx= {-1,0,0,1};
		int[] dy= {0,-1,1,0};
		visited[taxiStartX][taxiStartY]=true;
		if(board[taxiStartX][taxiStartY]!=0&&board[taxiStartX][taxiStartY]!=-1)
			find=board[taxiStartX][taxiStartY];
		//���ÿ� ���ÿ� �ٷ� �մ��� �¿���� ����
		
		boolean passengerFlag=false;
		int levelFlag=0;
		
		while(!qx.isEmpty()) {
			int X=qx.poll();
			int Y=qy.poll();
			if(boardLevel[X][Y]==levelFlag&&passengerFlag) {
				//�մ� ž�� ���� ���ϱ�
				passengerFlag=false;
				Collections.sort(personQueue);
				int tempX=personQueue.get(0).startX;
				int tempY=personQueue.get(0).startY;
				personQueue.clear();
				find=board[tempX][tempY];
				qx.clear();
				qy.clear();
				visitedClear(N);
				qx.add(tempX);
				qy.add(tempY);
				visited[tempX][tempY]=true;
				continue;
			}
			if(engineVisited[X][Y]==0) {
				System.out.print("-1");
				System.exit(0);
			}
			for(int i2=0;i2<4;i2++) {
				int newX=X+dx[i2];
				int newY=Y+dy[i2];
				if(newX>=1&&newX<=N&&newY>=1&&newY<=N&&!visited[newX][newY]&&board[newX][newY]!=-1) {
					engineVisited[newX][newY]=engineVisited[X][Y]-1;
					boardLevel[newX][newY]=boardLevel[X][Y]+1;
					remainEngine=engineVisited[newX][newY];
					if(find==0) {//�մ��� �ýÿ� ���� ��
						if(board[newX][newY]!=0&&!passengers[board[newX][newY]].check) {//�մ��� ������ ��
							passengerFlag=true;
							levelFlag=boardLevel[newX][newY];
							personQueue.add(passengers[board[newX][newY]]);
							qx.add(newX);
							qy.add(newY);
							visited[newX][newY]=true;
							/*find=board[newX][newY];
							qx.clear();
							qy.clear();
							visitedClear(N);
							qx.add(newX);
							qy.add(newY);
							visited[newX][newY]=true;
							break;*/
						}
						else {//�ܼ� ���� ������ ��
							qx.add(newX);
							qy.add(newY);
							visited[newX][newY]=true;
						}
					}
					else {//�մ��� �ýÿ� ���� ��
						if(passengers[find].arriveX==newX&&passengers[find].arriveY==newY) {
							//�������� �������� ���
							int count=engineVisited[passengers[find].startX][passengers[find].startY]
									-engineVisited[passengers[find].arriveX][passengers[find].arriveY];
							remainEngine+=(count*2);
							engineVisited[newX][newY]=remainEngine;
							passengers[find].check=true;
							qx.clear();
							qy.clear();
							visitedClear(N);
							boardLevel[newX][newY]=1;
							personCount++;
							if(personCount==M) {
								System.out.print(remainEngine);
								System.exit(0);
							}
							qx.add(newX);
							qy.add(newY);
							visited[newX][newY]=true;
							
							if(board[newX][newY]!=0&&!passengers[board[newX][newY]].check)
								//�մ��� �ٷ� ����ϰ� ���� ���
								find=board[newX][newY];
							else
								find=0;
							break;
						}
						else {//�������� �������� ������ ���
							qx.add(newX);
							qy.add(newY);
							visited[newX][newY]=true;
						}
					}
				}
			}
		}
		System.out.print("-1");
		input.close();
	}
}