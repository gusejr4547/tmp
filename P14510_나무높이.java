import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P14510_나무높이 {
	static int T, N, maxH, ans;
	static int[] tree, need;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			ans = 0;
			maxH = 0;
			tree = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int h = Integer.parseInt(st.nextToken());
				tree[i] = h;
				maxH = Math.max(maxH, h);
			}

			int odd = 0;
			int even = 0;
			// even은 2개의 odd로 변경 가능 하다, even우선으로 채운다
			for (int i = 0; i < N; i++) {
				int n = maxH - tree[i];
				even += n / 2;
				odd += n % 2;
			}

			// even - odd > 0 면 균형 맞추기 고려
			int tmp = Math.max(even - odd, 0);
			int tmpE = tmp * 2 / 3;
			int tmpO = tmp * 2 / 3;
			if (tmp * 2 % 3 == 1) {
				tmpO++;
			} else if (tmp * 2 % 3 == 2) {
				tmpE++;
			}

			even -= tmp - tmpE;
			odd += tmpO;

			int equal = Math.min(odd, even);
			// equal 수 만큼은 연속된 날
			ans = equal * 2;
			// odd가 남아 있으면
			if (odd - equal > 0) {
				ans += (odd - equal) * 2 - 1;
			}
			// even이 남아있으면
			if (even - equal > 0) {
				ans += (even - equal) * 2;
			}

			System.out.println("#" + tc + " " + ans);
		}
	}

}
