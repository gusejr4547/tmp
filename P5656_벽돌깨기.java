import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P5656_벽돌깨기 {

	static int T, N, W, H, min;
	static int[][] map;
	static int[] Heights; // 각 라인별 높이
	static int[] dy = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new int[H][W];

			// 벽돌 위치 입력
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 0 ~ W-1번까지 차례대로 쏘아본다
			for (int i = 0; i < W; i++) {
				shoot(i, 1, map);
			}

		}
	}

	static void shoot(int line, int count, int[][] map) {
		if (count == N) {
			// 구슬 쏜 횟수 == N이면 종료
			// 남은 block 개수를 min에 비교해서 저장

			return;
		}

		// 벽돌 위치 정보를 새로운 2차원 배열에 복사
		int[][] copyMap = copy(map);

		// line의 가장 처음 만나는 벽돌을 Brick 객체에 담고 queue에 담는다
		// queue에 들어갈때 그 벽돌을 copyMap에서 지운다
		Queue<Brick> queue = new ArrayDeque<>();
		for (int i = 0; i < H; i++) {
			if (copyMap[i][line] != 0) {
				queue.add(new Brick(i, line, copyMap[i][line]));
				copyMap[i][line] = 0;
				break;
			}
		}

		// queue를 이용하여 벽돌 No 범위에 들어오는 벽돌을 queue에 집어 넣고
		// queue가 빌때까지 반복한다.
		while (!queue.isEmpty()) {
			Brick b = queue.poll();

			// 깨지는 범위
			for (int k = 1; k < b.No; k++) {
				// 4방향
				// 벽돌이 위치에 있으면 queue에 넣고 copyMap에 0으로 표시 => 다음 탐색때 중복 되지 않게
				for (int i = 0; i < 4; i++) {
					int ny = b.y + dy[i] * k;
					int nx = b.x + dx[i] * k;

					if (ny >= 0 && nx >= 0 && ny < H && nx < W && copyMap[ny][nx] != 0) {
						queue.offer(new Brick(ny, nx, copyMap[ny][nx]));
						copyMap[ny][nx] = 0;
					}
				}
			}
		}

		// 빈공간이 있을 경우 벽돌은 밑으로 떨어진다
		// 라인 별로 스택에 넣고 아래서 부터 꺼내서 채움
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		for (int j = 0; j < W; j++) {
			// 스택넣고 있던자리 0으로 변경
			for (int i = 0; i < H; i++) {
				if (copyMap[i][j] != 0) {
					stack.push(copyMap[i][j]);
					copyMap[i][j] = 0;
				}
			}
			// 빼서 채우기
			int i = H - 1;
			while (!stack.isEmpty()) {
				copyMap[i][j] = stack.pop();
				i--;
			}
		}

		// 다음 구슬을 쏠 line을 정해서 shoot한다
		for (int i = 0; i < W; i++) {
			shoot(i, count + 1, copyMap);
		}
	}

	static int[][] copy(int[][] map) {
		int[][] temp = new int[H][];
		for (int i = 0; i < H; i++) {
			temp[i] = map[i].clone();
		}
		return temp;
	}

	static class Brick {
		int y, x, No;

		public Brick(int y, int x, int no) {
			this.y = y;
			this.x = x;
			No = no;
		}

	}
}
