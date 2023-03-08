import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P17831_대기업승범이네 {
	static int N;
	static List<Integer>[] adj;
	static int[][] memoi;
	// memoi[i][0] : i 노드를 사수로 선택하는 경우 최대값, memoi[i][1] : i 노드를 사수로 선택하지 않는 경우 최대값

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<Integer>();
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int to = 2; to <= N; to++) {
			int from = Integer.parseInt(st.nextToken());
			adj[from].add(to);
		}

		dfs(1); // 1번이 승범이 root
	}

	static void dfs(int node) {
		
	}
}
