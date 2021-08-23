/* Class �� ���� ���밪�� ���� ���� ����
 * ������ ���밪����g
 * 
 */
package DataStructure2;

import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class boj11286_���밪�� {

	static class abs_number implements Comparable<abs_number>{
		int abs, n;

		abs_number(int abs, int number) {
			this.abs = abs;
			n = number;
		}
		
		@Override
		public int compareTo(abs_number o) {
			if(this.abs == o.abs) {
				return this.n > o.n ? 1 : -1;	//������ ����
			}
			else 
				return this.abs > o.abs ? 1:-1;
				
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		PriorityQueue<abs_number> heap = new PriorityQueue<abs_number>();
		int n = Integer.parseInt(br.readLine());

		while (n-- > 0) {
			int input = Integer.parseInt(br.readLine());
			if (input == 0) {
				if (heap.isEmpty())
					sb.append(input + "\n");
				else
					sb.append(heap.poll().n + "\n");
			} else
				heap.offer(new abs_number(Math.abs(input),input));
		}

		System.out.println(sb.toString());
	}

}
