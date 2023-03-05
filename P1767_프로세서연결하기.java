import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P1767_프로세서연결하기 {

	static int T, N, coreMax, min;
	static int[][] map;
	static int[] dy = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dx = { 0, 0, -1, 1 };
	static List<Pos> list = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			coreMax = 0;
			min = Integer.MAX_VALUE;
			list.clear();

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int n = Integer.parseInt(st.nextToken());
					map[i][j] = n;
					// 각 벽에 붙은 core는 전선 사용할 필요가 없음
					if (map[i][j] == 1 && i != 0 && i != N - 1 && j != 0 && j != N - 1) {
						list.add(new Pos(i, j));
					}
				}
			}

			dfs(0, 0, 0, map);

			System.out.println("#" + tc + " " + min);
		}
	}

	static void dfs(int resIdx, int len, int connectCnt, int[][] map) {
		if (resIdx == list.size()) {
			// 전부 선택 완료
			// 1. 연결된 core 수가 많은것 우선
			// 2. 같으면 min 값과 비교해서 작은거 넣기
			if (coreMax < connectCnt) {
				coreMax = connectCnt;
				min = len;
			} else if (coreMax == connectCnt) {
				min = Math.min(min, len);
			}

//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println(len + " " + connectCnt);
			return;
		}

		// 가지치기
		// 중간에 남은 core갯수를 다 합해도 coreMax보다 적은경우 중단
		if (coreMax > connectCnt + (list.size() - resIdx)) {
			return;
		}

		Pos p = list.get(resIdx);
		// 방향 선택
		for (int d = 0; d < 4; d++) {
			int[][] copyMap = copy(map);
			if (canConnect(p.y, p.x, d, copyMap)) {
				dfs(resIdx + 1, len + connect(p.y, p.x, d, copyMap), connectCnt + 1, copyMap);
			}
		}

		// 연결 안함
		int[][] copyMap = copy(map);
		dfs(resIdx + 1, len, connectCnt, copyMap);

	}

	static int connect(int sy, int sx, int dir, int[][] map) {
		int y = sy + dy[dir];
		int x = sx + dx[dir];
		int count = 0;
		while (true) {
			map[y][x] = 2;
			count++;

			y = y + dy[dir];
			x = x + dx[dir];
			// 범위 check
			if (y < 0 || y >= N || x < 0 || x >= N) {
				return count;
			}
		}
	}

	// (sy,sx)에서 dir방향으로 연결 할 수 있는가?
	static boolean canConnect(int sy, int sx, int dir, int[][] map) {
		// 상 하 좌 우
		if (dir == 0) {
			for (int i = sy - 1; i >= 0; i--) {
				if (map[i][sx] != 0) {
					return false;
				}
			}
		} else if (dir == 1) {
			for (int i = sy + 1; i < N; i++) {
				if (map[i][sx] != 0) {
					return false;
				}
			}

		} else if (dir == 2) {
			for (int j = sx - 1; j >= 0; j--) {
				if (map[sy][j] != 0) {
					return false;
				}
			}

		} else if (dir == 3) {
			for (int j = sx + 1; j < N; j++) {
				if (map[sy][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	static int[][] copy(int[][] map) {
		int[][] newMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		return newMap;
	}

	static class Pos {
		int y, x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}

}
