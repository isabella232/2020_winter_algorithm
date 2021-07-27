package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj9663_NQueen {
	static int n;
	static int map[][];
	static boolean visit[][];
	static int result = 0;

	static int queen[]; // queen[��] = ��

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		queen = new int[n];
		
//		Arrays.fill(queen, -18);
		backTracking(0);

		System.out.println(result);
	}

	// idx : ��
	private static void backTracking(int idx) {
		// Ż�� ����
		if (idx == n ) {
			result++;
			return;
		}

		// i : ����
		for (int i = 0; i < n; i++) {
			// ù �� (�� x)
			queen[idx] = i;
			// check true�� ���� ������/ ���� ���� �� ������ ���� ������ �����ؼ� Ȯ��
			if (idx == 0 || check(idx))
				backTracking(idx + 1);

		}
	}

	public static boolean check(int check) {
		for (int j = 0; j < check; j++) {
			// queen[j(��)] : ���� == queen[check(���� ��)] : ����
			// j- check(�� �Ÿ�) == ���Ÿ� -> �밢�� �� ����
			if (queen[j] == queen[check] ||check - j == Math.abs(queen[j] - queen[check]))
				return false;
		}

		return true;
	}

}
