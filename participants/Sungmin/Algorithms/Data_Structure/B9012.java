/*
 * �а����� ����� ������ �̿��� ��ȣ �Ǻ���
 * ��ȣ�� ¦�� ���߾� push/pop�� ���� ��/������ �Ǻ��Ѵ�.
 * (�ݴ� �Ұ�ȣ�� ������ ���� �Ұ�ȣ�� ���� pop, �ƴ϶�� NO)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
public class B9012 {
	public static void main(String[] args) throws IOException{
		Stack<Character> array = new Stack<Character>();
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int x=Integer.parseInt(input.readLine());
		for(int i=0;i<x;i++) {
			array.clear();
			String s = input.readLine();
			for(int i1=0;i1<s.length();i1++) {
				if(s.charAt(i1)=='(')//push
					array.push(s.charAt(i1));
				else if(s.charAt(i1)==')') {//pop
					if(array.size()==0) {//¦�� �� �´� ���
						sb.append("NO\n");
						break;
					}
					else array.pop();
				}
				if(i1==s.length()-1) {
					if(array.size()==0)sb.append("YES\n");
					else sb.append("NO\n");
				}
			}
		}
		System.out.print(sb);
		input.close();
	}
}