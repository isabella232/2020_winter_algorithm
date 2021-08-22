/*
 * ��縶�ƴ�. �� �ϸ��� ���� �ϴ� ���� �� �ϴ� ����� �� �ִ밪�� ���Ž����ָ� ��
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class B14501 {
	static int max=0;
	static int x;
	public static void main(String[] args) throws IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		x=Integer.parseInt(input.readLine());
		int[] days = new int[x+1];
		int[] pays = new int[x+1];
		int[] dp = new int[x+2];
		for(int i=1;i<=x;i++) {
			StringTokenizer stk=new StringTokenizer(input.readLine());
			days[i]=Integer.parseInt(stk.nextToken());
			pays[i]=Integer.parseInt(stk.nextToken());
		}
		for(int i=1;i<=x;i++) {//�� ���� �ϴ� ���� �� �ϴ� ���� ����
			if(i+days[i]>x+1) {//�ٹ� �ϼ� �ʰ��� �� �� �� ����
				dp[i+1]=Math.max(dp[i+1], dp[i]);
			}
			else {
				dp[i+days[i]]=Math.max(dp[i+days[i]], pays[i]+dp[i]);//���� �ϴ� ���
				dp[i+1]=Math.max(dp[i+1], dp[i]);//���� �� �ϴ� ���
			}
		}
		System.out.print(dp[x+1]);
		input.close();
	}
}