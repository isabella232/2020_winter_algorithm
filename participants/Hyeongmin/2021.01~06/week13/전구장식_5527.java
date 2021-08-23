package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class �������_5527 {
	static int originLamp[];
	static int Lamp[];
	static ArrayList<Integer> store = new ArrayList<Integer>(); // ���� ��ġ ���

	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String temp[] = br.readLine().split(" ");

		originLamp = new int[n];
		Lamp = new int[n];
		for (int i = 0; i < n; i++) {
			originLamp[i] = Integer.parseInt(temp[i]);
			Lamp[i] = Integer.parseInt(temp[i]);
		}
		/*--------------input-----------*/

		patternCheck(originLamp);

		// 0, 1, 2�̻�

		if (store.size() <=2)
			result = originLamp.length;
		
		else if(store.size()==3)
			result = Math.max(result, store.get(2));
		else if (store.size() >3 ) {
				
			for (int i = 0; i < store.size() - 3; i++) {
				if (i == 0)
					result = Math.max(result, store.get(i + 2));
				else
					result = Math.max(result, store.get(i + 3) - store.get(i));
			}
		}
		
		//���� ó�� ����� 2�϶�
		if(originLamp.length==2)
			System.out.println(2);
		else
			System.out.println(result);
	}

	// �ʱ�ȭ
	public static void init() {
		for (int i = 0; i < originLamp.length; i++)
			Lamp[i] = originLamp[i];
	}

	// ���� �ٲٱ�
	public static void changeLamp(int start, int end) {

		for (int i = start; i <= end; i++) {
			if (Lamp[i] == 0)
				Lamp[i] = 1;
			else
				Lamp[i] = 0;
		}
	}

	// �������� ī��Ʈ
	public static void patternCheck(int temp[]) {
		for (int j = 0; j < temp.length - 1; j++) {
			// ���� ������ �ƴҶ�
			if (temp[j] == temp[j + 1])
				store.add(j + 1);

			// ���� �����϶�
		}

	}
}
