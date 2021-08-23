/*
 * �ִ���, �ּ��� ���� �����Ͽ� Ǫ�� ����
 * ���� �����ϴ� remove �Լ��� ����ϸ� �ð� �ʰ��� ���� ���� �߻�
 * �� ������ �ε����� �־� �� ������ ���������� visited�� ������ ��� �������ִ� ������� ������ �ذ�
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B7662 {
	static class Pair implements Comparable<Pair>{
		int value;
		int index;
		Pair(int value, int index){
			this.value=value;
			this.index=index;
		}
		
		@Override
		public int compareTo(Pair obj) {
			if(this.value<obj.value)return -1;
			else if(this.value==obj.value)return 0;
			return 1;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		PriorityQueue<Pair> queuemin = new PriorityQueue<Pair>();
		PriorityQueue<Pair> queuemax = new PriorityQueue<Pair>(Collections.reverseOrder());
		int n=Integer.parseInt(input.readLine());
		for(int i=0;i<n;i++) {
			queuemin.clear();
			queuemax.clear();
			int x=Integer.parseInt(input.readLine());
			boolean[] visited=new boolean[x];
			for(int i1=0;i1<x;i1++) {
				StringTokenizer stk=new StringTokenizer(input.readLine());
				String judge=stk.nextToken();
				int value=Integer.parseInt(stk.nextToken());
				if(judge.equals("I")) {
					queuemin.add(new Pair(value,i1));
					queuemax.add(new Pair(value,i1));
				}
				else {
					if(queuemin.isEmpty())
						continue;
					if(value>0) {
						visited[queuemax.poll().index]=true;
					}
					else {
						visited[queuemin.poll().index]=true;
					}
				}
				while(!queuemax.isEmpty()&&visited[queuemax.peek().index])
					queuemax.poll();
				while(!queuemin.isEmpty()&&visited[queuemin.peek().index])
					queuemin.poll();
			}
			if(queuemin.isEmpty()) {
				sb.append("EMPTY\n");
			}
			else {
				sb.append(queuemax.poll().value+" "+queuemin.poll().value+"\n");
			}
		}
		System.out.print(sb);
		input.close();
	}
}
