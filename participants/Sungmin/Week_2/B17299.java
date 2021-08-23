import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
public class B17299 {
	public static void main(String[] args) {
		try {
			Stack<Integer> array = new Stack<Integer>();
			BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
			StringBuilder output = new StringBuilder();
			int x=Integer.parseInt(input.readLine());
			int[] nums=Arrays.asList(input.readLine().split(" ")).stream().mapToInt(Integer::parseInt).toArray();
			int[] answer=new int[x];
			int[] f=new int[1000001];
			for(int i=0;i<answer.length;i++)answer[i]=-1;
			for(int i=0;i<nums.length;i++)f[nums[i]]+=1;
			for(int i=0;i<x;i++) {
				while(array.size()!=0&&f[nums[array.peek()]]<f[nums[i]])
					answer[array.pop()]=nums[i];
				array.push(i);
			}
			for(int i=0;i<answer.length;i++)output.append(answer[i]+" ");
			System.out.print(output);
			input.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}