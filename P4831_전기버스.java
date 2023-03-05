import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P4831_전기버스 {
	static int T, K, N, M;
	static List<Integer> charger = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			charger.clear();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				charger.add(Integer.parseInt(st.nextToken()));
			}

			int cIdx = 0;
			int pos = 0;
			int count = 0;

			while (pos + K < N) {
				int tmp = cIdx;
				while (cIdx < M && pos + K >= charger.get(cIdx)) {
					cIdx++;
				}

				if (tmp == cIdx) {
					count = 0;
					break;
				}
				pos = charger.get(cIdx - 1);
				count++;
			}

			System.out.println("#" + tc + " " + count);
		}
	}

}
