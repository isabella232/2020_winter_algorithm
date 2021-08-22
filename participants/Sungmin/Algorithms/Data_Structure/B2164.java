/*
 * front������ ������, back������ ���Ը� �Ͼ�� ������ Queue�� ���
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
public class B2164 {
	public static void main(String[] args) throws IOException{
		Queue<Integer> array = new LinkedList<>();
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int x=Integer.parseInt(input.readLine());
		for(int i=1;i<=x;i++)
			array.add(i);
		while(array.size()!=1) {
			array.poll();
			array.add(array.poll());
		}
		System.out.print(array.poll());
		input.close();
	}
}