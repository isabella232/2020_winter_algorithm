/*
 * �� ������ �Ⱦ� ����2
 * ����Ʈ Ž�� �� for-each ������ ���⵵�� �� ���ٴ� �� ������
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class B16937 {
	static class Pair{
		int x;
		int y;
		Pair(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	static int max=0;
	static ArrayList<Pair> making=new ArrayList<Pair>();
	static ArrayList<Pair> papers=new ArrayList<Pair>();
	public static void main(String[] args) throws IOException{
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk=new StringTokenizer(input.readLine());
		int x=Integer.parseInt(stk.nextToken());
		int y=Integer.parseInt(stk.nextToken());
		int n=Integer.parseInt(input.readLine());
		for(int i=0;i<n;i++) {
			stk=new StringTokenizer(input.readLine());
			int paperX=Integer.parseInt(stk.nextToken());
			int paperY=Integer.parseInt(stk.nextToken());
			if((paperX>x&&paperX>y)||(paperY>x&&paperY>y))
				continue;
			else if((paperX>x&&paperX<=y)||(paperY>y&&paperY<=x)) {//��Ե� ������ �� ���� �� xy ����
				int temp=paperX;
				paperX=paperY;
				paperY=temp;
			}
			papers.add(new Pair(paperX,paperY));
		}
		int i=0;
		for(Pair obj1:papers) {
			int i1=0;
			makingPossible(obj1,x,y);//�ϳ��� �������� ��������� ���� ����
			int firstpaper=obj1.x*obj1.y;//ó�� ���� ���� ũ��
			int secondpaper=0;//���⿡ �ٸ� �ٿ��� ���� ũ�Ⱑ ��
			for(Pair obj2:papers) {
				if(i==i1) {
					i1++;
					continue;
				}
				int tempsize=compare(obj2);
				if(tempsize>secondpaper)
					secondpaper=tempsize;
				i1++;
			}
			if(secondpaper==0) {
				i++;
				continue;
			}
			if((firstpaper+secondpaper)>max)
				max=firstpaper+secondpaper;
			i++;
		}
		System.out.print(max);
		input.close();
	}
	static void makingPossible(Pair obj,int x, int y) {
		making.clear();
		if(((y-obj.y)>=0)&&((x-obj.x)>=0)) {
			making.add(new Pair(obj.x,(y-obj.y)));
			making.add(new Pair((x-obj.x),y));
		}
		if(((y-obj.x)>=0)&&((x-obj.y)>=0)) {
			making.add(new Pair(obj.y,(y-obj.x)));
			making.add(new Pair((x-obj.y),y));
		}
	}
	static int compare(Pair obj) {
		int result=0;
		for(Pair index:making) {
			if(((obj.x<=index.x)&&(obj.y<=index.y))||((obj.y<=index.x)&&(obj.x<=index.y)))
				result=obj.x*obj.y;
		}
		return result;
	}
}