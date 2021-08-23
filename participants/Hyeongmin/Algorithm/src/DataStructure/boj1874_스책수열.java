/* boj 1874 ���� ����
 * ���� ������ ���� ���� stack�� peek���� ���� �� �̸� �ȵ�
 */
package DataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class boj1874_��å���� {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder(); 
		
		int n = Integer.parseInt(br.readLine());
		Queue<Integer> q_list = new LinkedList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i=1;i<=n;i++)
			q_list.offer(i);
		
		for(int i=0;i<n;i++) {
			int value = Integer.parseInt(br.readLine());
			
			while(true) {
				if(stack.isEmpty()) {
					stack.push(q_list.poll());
					bw.append("+\n");
				}
				else {
					//�ȵǴ� ���
					if(stack.peek()>value) {
						System.out.println("NO");
						return;
					}
					
					//�����ϴ� ���
					if(stack.peek()==value) {
						stack.pop();
						bw.append("-\n");
						break;
					}
					else {
						if(q_list.isEmpty()) {
							System.out.println("NO");
							return;
						}
						stack.push(q_list.poll());
						bw.append("+\n");
					}
				}
			}
		}
		System.out.println(bw.toString());
	}

}
