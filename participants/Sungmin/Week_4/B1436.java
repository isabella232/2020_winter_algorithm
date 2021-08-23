import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class B1436 {
	public static void main(String[] args) {
		try {
			BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter output=new BufferedWriter(new OutputStreamWriter(System.out));
			int x=Integer.parseInt(input.readLine());
			int count=0;
			int num=665;
			int digitCount,temp;
			//ó���� num�� 1�� ������Ű�鼭 ��Ʈ�� ��ȯ->.equals("6")�� �̿��Ͽ����� �޸� �ʰ� �߻�. �ڹ� �̿� �� ����
			while(x!=count) {
				digitCount=0;
				num++;
				temp=num;
				while(temp!=0) {
					if(temp%10==6) digitCount++;
					else digitCount=0;
					if(digitCount==3) break;
					temp/=10;
				}
				if(digitCount==3) count++;
			}
			output.write(String.valueOf(num));
			output.flush();
			output.close();
			input.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}