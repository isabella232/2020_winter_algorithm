import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B17478 {
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) {
		try {
			BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
			int n=Integer.parseInt(input.readLine());
			sb.append("��� �� ��ǻ�Ͱ��а� �л��� ������ �������� ã�ư� ������."+"\n");
			Recursion(n,0);
			System.out.print(sb);
			input.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	static void Recursion(int n, int count) {
		for(int i=0;i<count;i++) sb.append("____");
		sb.append("\"����Լ��� ������?\""+"\n");
		if(count==n) {
			for(int i=0;i<count;i++) sb.append("____");
			sb.append("\"����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����\""+"\n");
			for(int i=0;i<count;i++) sb.append("____");
			sb.append("��� �亯�Ͽ���."+"\n");
			return;
		}
		else {
			for(int i=0;i<count;i++) sb.append("____");
			sb.append("\"�� ����. �������� �� �� ����⿡ �̼��� ��� ������ ����� ������ �־���."+"\n");
			for(int i=0;i<count;i++) sb.append("____");
			sb.append("���� ������� ��� �� ���ο��� ������ ������ �߰�, ��� �����Ӱ� ����� �־���."+"\n");
			for(int i=0;i<count;i++) sb.append("____");
			sb.append("���� ���� ��κ� �ǾҴٰ� �ϳ�. �׷��� ��� ��, �� ���ο��� �� ���� ã�ƿͼ� ������.\""+"\n");
			Recursion(n,count+1);
		}
		for(int i=0;i<count;i++) sb.append("____");
		sb.append("��� �亯�Ͽ���."+"\n");
	}
}