import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P13416_주식투자 {

	static int T, N, sum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			sum = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());

				int max = 0;
				max = Math.max(max, A);
				max = Math.max(max, B);
				max = Math.max(max, C);

				sum += max;
			}
			
			System.out.println(sum);
		}
	}

}
