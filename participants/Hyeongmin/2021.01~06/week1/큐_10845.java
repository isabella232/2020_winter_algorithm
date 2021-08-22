package week1;

import java.io.*;
import java.util.*;

public class ť_10845 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	//��� ��ü (�迭���� �ð� �ʰ�)
		
		int n = Integer.parseInt(br.readLine());	// ��ɾ� ����
		
		Queue<Integer> q = new LinkedList<Integer>();
				
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		
		int back_num = -1;
		
		for(int i =0;i<n;i++) {
			String str = br.readLine();
			StringTokenizer a = new StringTokenizer(str);
			
			String command = a.nextToken();
			switch (command) {
			case "push":
				command = a.nextToken();
				q.offer(Integer.parseInt(command));
				back_num = Integer.parseInt(command);
				break;
			
			case "pop":
				if(q.size()==0) {
					bw.write(-1+"\n");
					break;
				}				
				int re = q.poll();
				
				bw.write(re+"\n");

				
				break;
				
			case "size":
				bw.write(q.size()+"\n");

				break;
				
			case "empty":
				if(q.size()==0) {
					bw.write(1+"\n");
					break;
				}
				else {
					bw.write(0+"\n");

					break;					
				}
				
			case "back":
				if(q.size()==0) {
					bw.write(-1+"\n");

					break;
				}
				bw.write(back_num+"\n");

				break;
				
			case "front":				
				if(q.size()==0) {
					bw.write(-1+"\n");

					break;
				}
				
				int re1 = q.peek();
				bw.write(re1+"\n");

				break;
			}
		}

		 bw.flush();
		 bw.close();
		 br.close();
	}
}
