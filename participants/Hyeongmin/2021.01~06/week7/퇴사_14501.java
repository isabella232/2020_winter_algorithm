package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ���_14501 {
	static ArrayList<Integer> sum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int t[] = new int[n + 2];
		int p[] = new int[n + 2];
		int dp[] = new int[n + 2];	//i�� ���� �ִ� ���� �� �մ� �ݾ�

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());	//i��
			p[i] = Integer.parseInt(st.nextToken());	//�ݾ�
		}
		
		int max = 0;
		
		for(int i=1;i<n+2;i++) {
			if(t[i] <= n+1)
				dp[i] = Math.max(dp[i], max);
				
				//��� ���� �׳� ���� �� �ִ� �ݾ�
				if(i+t[i]<=n+1)
					dp[i+t[i]] = Math.max(dp[i+t[i]], p[i]+dp[i]); //���� ����� ��, ���� ���ŵǴ°�
				
				max = Math.max(dp[i], max);	//i�� ���� ���� �� �ִ� �ִ�ݾ�
		}	
		
		System.out.println(max);
		
		br.close();
		
	}

}
