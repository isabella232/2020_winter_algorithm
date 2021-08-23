import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B5527 {
	
	static int start(int[] board, int cali) {//cali�� 0�̸� ������ ����, 1�̸� ������ ����
		int rightCount=0;//����� ���� ���
		boolean changeFlagOne=false;//ó�� Ʋ���� ��� �÷���
		boolean changeFlagTwo=false;//Ʋ�ȴ� �¾Ҵ� Ʋ���� ���� ������ ������
		int count=0;//�� ī��Ʈ
		int max=0;
		for(int i=0;i<board.length;i++) {
			int i1=(i+cali)%2;
			if(board[i]==i1) {//������ ����
				if(changeFlagOne&&!changeFlagTwo) {//�� �� �ٲ� ���¸� ���⼭ ����
					changeFlagTwo=true;
					count++;
					rightCount=1;
				}
				else {//�ܼ� ī��Ʈ ��
					rightCount++;
					count++;
				}
			}
			else {//������ Ʋ��
				if(!changeFlagOne) {//ó�� Ʋ���� ����
					changeFlagOne=true;
					count=1+rightCount;
					rightCount=0;
				}
				else {//ó�� Ʋ���� �� �ƴ�
					if(changeFlagTwo) {//Ʋ�ȴ� �¾Ҵٰ� �ٽ� Ʋ��
						changeFlagOne=true;
						changeFlagTwo=false;
						max=Math.max(max, count);
						count=1+rightCount;
						rightCount=0;
					}
					else {//ó�� Ʋ���� �� ��� ������
						count++;
					}
				}
			}
		}
		return Math.max(max, count);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(input.readLine());
		String[] s=input.readLine().split(" ");
		int[] board=new int[n];
		for(int i=0;i<n;i++)
			board[i]=Integer.parseInt(s[i]);
		//�Է� �Ϸ�
		System.out.print(Math.max(start(board,0), start(board,1)));
		input.close();
	}
}