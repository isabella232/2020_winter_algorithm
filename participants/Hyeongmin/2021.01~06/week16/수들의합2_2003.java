package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ��������2_2003 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String temp[] = br.readLine().split(" ");
		int n = Integer.parseInt(temp[0]);
		int m = Integer.parseInt(temp[1]);

		temp = br.readLine().split(" ");

		int count = 0;

		int startidx = 0, endidx = 0;
		int sum = Integer.parseInt(temp[0]); // �ʱ� ù��°

		while (endidx < temp.length) {
			
			// M���� �۰� ���� �������� �ʾ��� ��
			if (sum < m && endidx!=temp.length-1)
				sum+=Integer.parseInt(temp[++endidx]);
			// M���� �������� ��
			else {
				if (sum == m)
					count++;
				
				if (startidx == endidx ) {
					
						sum -= Integer.parseInt(temp[startidx++]); // ������ ����
						if(++endidx<temp.length)
							sum += Integer.parseInt(temp[endidx]); // ������ �߰�
					
				} else
					sum-=Integer.parseInt(temp[startidx++]);	//��ĭ ������ ���� �� ����
			}
		}

		System.out.println(count);
	}


}
