/*
 * Queue�� �߰������� �켱���� ������ ����ִ� �迭/�켱���� ���� ������ �߰��Ͽ� ����
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
public class B1966 {
	public static void main(String[] args) throws IOException{
		StringBuilder sb=new StringBuilder();
		String[] s;
		Queue<Integer> array = new LinkedList<Integer>();
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(input.readLine());
		for(int i=0;i<n;i++) {
			array.clear();
			s = input.readLine().split(" ");
			int[] prior=new int[Integer.parseInt(s[0])];
			int objectindex=Integer.parseInt(s[1]);
			s = input.readLine().split(" ");
			for(int i1=0;i1<prior.length;i1++) {
				array.add(Integer.parseInt(s[i1]));
				prior[i1]=Integer.parseInt(s[i1]);
			}
			Arrays.sort(prior);
			int priorindex=prior.length-1;
			int count=0;
			while(!array.isEmpty()) {
				if(array.peek()<prior[priorindex]){//�� ���� �켱������ �ִ� ���
					if(objectindex==0) objectindex=array.size()-1;//��ǥ �ε��� �ʱ�ȭ
					else objectindex-=1;
					array.add(array.poll());
				}
				else {//������ ���� ����
					count++;
					if(objectindex==0) {
						sb.append(count+"\n");
						break;
					}
					array.poll();
					priorindex--;
					objectindex--;
				}
			}
		}
		System.out.print(sb);
		input.close();
	}
}