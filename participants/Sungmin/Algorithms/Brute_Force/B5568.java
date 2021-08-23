/*
 * TreeMap�� Key���� �����ϴٴ� ������ �̿��� �ڵ�
 * �������� ��Ʈ��ŷ�� Ȱ���� ������ �����Ͽ� �����Ͽ���.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.TreeMap;
public class B5568 {
	static LinkedList<Integer> permutation=new LinkedList<>();
	static TreeMap<Integer,Integer> map=new TreeMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(input.readLine());
		int k=Integer.parseInt(input.readLine());
		boolean[] visited=new boolean[n];
		int[] nums=new int[n];
		for(int i=0;i<n;i++)
			nums[i]=Integer.parseInt(input.readLine());
		permu(n,k,nums,visited);
		System.out.print(map.size());
		input.close();
	}
	static void permu(int n,int r,int[] nums,boolean[] visited){
		if(permutation.size()==r) {
			StringBuilder sb=new StringBuilder();
			for(int i:permutation)
				sb.append(i);
			int temp=Integer.parseInt(sb.toString());
			if(!map.containsKey(temp))
				map.put(temp, 1);
			return;
		}
		for(int i=0;i<nums.length;i++) {
			if(visited[i])continue;
			permutation.add(nums[i]);
			visited[i]=true;
			permu(n,r,nums,visited);
			permutation.removeLast();
			visited[i]=false;
		}
	}
}