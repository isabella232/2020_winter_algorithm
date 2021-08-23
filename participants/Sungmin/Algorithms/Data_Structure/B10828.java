/*
 * �ܼ� ���� ���� ����. ���ǹ��� ���ະ�� �ѹ����� ����ǵ��� else if���� ���� ���� ����Ʈ
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class B10828 {
	public static void main(String[] args) throws IOException{
		List<String> array = new ArrayList<String>();
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int x=Integer.parseInt(input.readLine());
		for(int i=0;i<x;i++) {
			String[] s = input.readLine().split(" ");
			if(s[0].equals("push"))
				array.add(s[1]);
			else if(s[0].equals("pop")) {
				if(array.size()==0) sb.append("-1\n");
				else {
					sb.append(array.get(array.size()-1)+"\n");
					array.remove(array.size()-1);
				}
			}
			else if(s[0].equals("empty"))
				if(array.size()==0) sb.append("1\n");
				else sb.append("0\n");
			else if(s[0].equals("top"))
				if(array.size()==0) sb.append("-1\n");
				else sb.append(array.get(array.size()-1)+"\n");
			else if(s[0].equals("size"))
				sb.append(array.size()+"\n");
		}
		System.out.print(sb);
		input.close();
	}
}