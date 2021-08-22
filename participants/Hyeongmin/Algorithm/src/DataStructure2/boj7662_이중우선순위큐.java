/* class �ڷ��� ������
 * ������ ������ index ��� -> �湮ǥ��(�����Ҷ�)
 * ���� ��������, �������� 2��
 * 
 */
package DataStructure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;


class Number implements Comparable<Number>{
	int index, number;
	
	Number(int idx, int n){
		this.index = idx; 
		this.number = n;
	}
	
	@Override
	public int compareTo(Number o) {
		if(this.number < o.number)
			return -1;
		else
			return 1;
		}
}
public class boj7662_���߿켱����ť {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		
		while(n-->0) {
			int t = Integer.parseInt(br.readLine());
			PriorityQueue<Number> max_heap = new PriorityQueue<Number>();	//��������
			PriorityQueue<Number> min_heap = new PriorityQueue<Number>(Collections.reverseOrder());	//��������
			
			ArrayList<Boolean> visit = new ArrayList<Boolean>();
			
			String temp[] = new String[2];
			int index = 0;
			while(t-->0) {
				
				temp = br.readLine().split(" ");
				
				if(temp[0].equals("I")) {
					
					Number data = new Number(index++, Integer.parseInt(temp[1]));
					max_heap.add(data);
					min_heap.add(data);
					
					visit.add(false);	//�湮���Ѱ� 
				}
					
				else {
					//�ִ밪 ���
					if(Integer.parseInt(temp[1])==1) {
						while(!min_heap.isEmpty()) {
							//�湮 ���ѰŸ�
							Number data = min_heap.poll();
							if(!visit.get(data.index)) {
								visit.set(data.index, true);
								break;
							}
						}
					}
					else {
						while(!max_heap.isEmpty()) {
							//�湮 ���ѰŸ�
							Number data = max_heap.poll();
							if(!visit.get(data.index)) {
								visit.set(data.index, true);
								break;
							}
						}
					}
					
				}
			}
			
			//���� ��
			if(!visit.contains(false))
				sb.append("EMPTY\n");
			else {
				
				while(!min_heap.isEmpty()) {
					Number data = min_heap.poll();
					if(!visit.get(data.index)) {
						sb.append(data.number+" ");
						break;
					}
				}
				
				while(!max_heap.isEmpty()) {
					Number data = max_heap.poll();
					if(!visit.get(data.index)) {
						sb.append(data.number+"\n");
						break;
					}
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}

