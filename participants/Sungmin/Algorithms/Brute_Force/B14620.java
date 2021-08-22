/*
 * �����丵 �ʹ� ������.
 * ���ڸ������ �ڶ�� ������ �� �׵θ����� ���� �� ���ٴ� Ư¡�� ������.
 * �� �࿭-2 �� �������� ���� ������ 3���� ��츦 ��� �̾Ƽ� �� ����� �ּҰ� ����ϸ� ��
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class B14620 {
	static int min=3000;
	public static void main(String[] args) throws IOException{
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(input.readLine());
		int[][] arrays=new int[n][n];
		boolean[][] visited=new boolean[n][n];
		for(int i=0;i<n;i++) {
			StringTokenizer stk = new StringTokenizer(input.readLine());
			for(int i1=0;i1<n;i1++)
				arrays[i][i1]=Integer.parseInt(stk.nextToken());
		}//�Է�
		int[] tempresult=new int[3];
		findValue(arrays,n,tempresult,0,visited);
		System.out.print(min);
		input.close();
	}
	static void findValue(int[][] arrays,int n,int[] tempresult,int r,boolean[][] visited) {
		if(r==3) {
			int result=0;
			for(int i=0;i<3;i++)
				result+=tempresult[i];
			if(result<min)
				min=result;
			return;
		}
		for(int i=1;i<n-1;i++) {
			for(int i1=1;i1<n-1;i1++) {
				if(visited[i][i1]||visited[i+1][i1]||visited[i-1][i1]||visited[i][i1+1]||visited[i][i1-1])continue;
				visited[i][i1]=true;
				visited[i+1][i1]=true;
				visited[i-1][i1]=true;
				visited[i][i1+1]=true;
				visited[i][i1-1]=true;
				tempresult[r]=arrays[i][i1]+arrays[i+1][i1]+arrays[i-1][i1]+arrays[i][i1+1]+arrays[i][i1-1];
				findValue(arrays,n,tempresult,r+1,visited);
				tempresult[r]=0;
				visited[i][i1]=false;
				visited[i+1][i1]=false;
				visited[i-1][i1]=false;
				visited[i][i1+1]=false;
				visited[i][i1-1]=false;
			}
		}
		
	}
}