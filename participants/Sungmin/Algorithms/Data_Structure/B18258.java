/*
 * LinkedList�� �̿��� �ܼ� ť�� ����
 * ������ ����ϰ� �Ͼ�� ������ LinkedList ����� �� �����ϴٰ� �Ǵ�
 * back�� ��� Ž���� ���� ���� �ð��� �ɸ� �� ���� push�� ������ ���ο� ������ ����� �ʱ�ȭ
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
public class B18258 {
	public static void main(String[] args) throws IOException{
		Queue<Integer> array = new LinkedList<>();
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int x=Integer.parseInt(input.readLine());
		int back=-1;
		for(int i=0;i<x;i++) {
			String[] s = input.readLine().split(" ");
			if(s[0].equals("push")) {
				back=Integer.parseInt(s[1]);
				array.add(back);
			}
			else if(s[0].equals("pop")) {
				if(array.size()==0) sb.append("-1\n");
				else sb.append(array.poll()+"\n");
			}
			else if(s[0].equals("size"))
				sb.append(array.size()+"\n");
			else if(s[0].equals("empty")) {
				if(array.size()==0) sb.append("1\n");
				else sb.append("0\n");
			}
			else if(s[0].equals("front")) {
				if(array.size()==0) sb.append("-1\n");
				else sb.append(array.peek()+"\n");
			}
			else {
				if(array.size()==0) sb.append("-1\n");
				else sb.append(back+"\n");
			}
		}
		System.out.print(sb);
		input.close();
	}
}