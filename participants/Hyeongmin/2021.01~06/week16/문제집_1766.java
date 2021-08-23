package week16;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

class D implements Comparable<D>{
	int number;
	int priority;
	
	D(int number, int priority){
		this.number = number;
		this.priority = priority;
	}
	
	@Override
	public int compareTo(D o) {
		if(this.priority> o.priority)
			return 1;
		//�켱������ �������� �������ں���
		else if(this.priority==o.priority) {
			if(this.number>o.number)
				return 1;
		}
		return-1;
	}
}
public class ������_1766 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		
		String temp[] = br.readLine().split(" ");
		int n = Integer.parseInt(temp[0]);
		int m = Integer.parseInt(temp[1]);
		
		boolean visit[] = new boolean[n+1];	//1���� ����
		
		PriorityQueue<D> list = new PriorityQueue<D>();
		
 		//m���� ����Ǯ����� ������
		for(int i=0;i<m;i++) {
			temp = br.readLine().split(" ");
			int number = Integer.parseInt(temp[0]);
			int backNumber = Integer.parseInt(temp[1]);

			visit[backNumber] = true;
			D data = new D(backNumber, number+1);	//�ٷεڿ� ���°� ���� ȿ���� ����
			list.add(data);
		}
		
		for(int i=1;i<=n;i++) {
			if(visit[i])
				continue;
			
			D data = new D(i, i);	//�ٷεڿ� ���°� ���� ȿ���� ����
			list.add(data);
		}
		
		while(!list.isEmpty()) {
			if(list.size()==1)
				bw.append(list.poll().number+"");
			else
				bw.append(list.poll().number+" ");
		}
		
		System.out.print(bw.toString());
		br.close();
	}

}
