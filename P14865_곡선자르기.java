import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 봉우리 시작점, 끝점 묶어서 저장하지 말고
// 시작점, 끝점 판별 가능하게 같이 저장하고 (x1, 1), (x2, -1) 이런식
// 오름차순 정렬해서
// 괄호 stack을 이용해 판별
public class P14865_곡선자르기 {

	static int N;
	static List<int[]> list = new ArrayList<>();
	static int[] up;
	static int[] down;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		// 선을 따라 가면서 0을 지나는 수직 선 => 위로 올라가는 선 & 아래로 내려가는 선을 만나면 1개의 세트로 만든다
		// 저장하는 값은 x축의 값

		// 첫번째 점 => 먼저 저장하고 , 마지막 점이랑 한번 더 이어 주어야 하므로 다른데도 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] before = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
		int[] first = before.clone();

		boolean find = false; // 탐색 중 true, 아니면 false
		int temp = 0; // 탐색중이 아닌데 아래로 내려가는 선이 나올 경우 값을 담아둠
		int open = 0;
		// 두번째 점부터
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			// 수직선이 만들어 질 때
			if (before[0] == x) {
				// 이전것 > 0 and 현재 < 0 => 아래로 내려가는 선
				if (before[1] > 0 && y < 0) {
					if (find) {
						// 탐색 중이라면 낮은 순서로 바꾼뒤 list에 추가하고 find를 false로
						if (open < x) {
							list.add(new int[] { open, x });
						} else {
							list.add(new int[] { x, open });
						}
						find = false;
					} else {
						// 탐색 중이 아니라면 임시 저장
						temp = x;
					}
				}
				// 위로 올라가는 선 => 탐색 시작
				else if (before[1] < 0 && y > 0) {
					find = true;
					open = x;
				}
			}
			// 현재 값으로 갱신
			before[0] = x;
			before[1] = y;
		}

		// 마지막 점과 첫번 째 점을 이어준다 => before - first
		// 수직선?
		if (before[0] == first[0]) {
			// 내려가는 선
			if (before[1] > 0 && first[1] < 0) {
				if (find) {
					if (open < first[0]) {
						list.add(new int[] { open, first[0] });
					} else {
						list.add(new int[] { first[0], open });
					}
				}
			}
			// 올라가는 선
			else if (before[1] < 0 && first[1] > 0) {
				find = true;
				open = first[0];
			}
		}

		// find가 true인 채로 끝났다면 open과 임시 저장된 temp를 사용해서 list에 추가
		if (find) {
			if (open < temp) {
				list.add(new int[] { open, temp });
			} else {
				list.add(new int[] { temp, open });
			}
		}

//		for (int[] l : list) {
//			System.out.print("(" + l[0] + " " + l[1] + ")");
//		}
//		System.out.println();

		// 시작점 기준으로 정렬
		Collections.sort(list, (n1, n2) -> n1[0] - n2[0]);
		// 다른 봉우리에 의해 포함되지 않는 봉우리 => 가장 바깥쪽 봉우리
		int cnt1 = 0;
		int close = Integer.MIN_VALUE;
		for (int i = 0; i < list.size(); i++) {
			int[] current = list.get(i);
			// 전의 close와 current의 open을 비교
			// close가 작으면 다른 봉우리에 포함되지 않음
			if (close < current[0]) {
				cnt1++;
				close = current[1];
			}
		}

		// 끝점 기준으로 정렬 => 다른 것을 포함하는 봉우리 보다 그 안에 들어오는 봉우리가 먼저 나올 것이다
		Collections.sort(list, (n1, n2) -> n1[1] - n2[1]);
		// 다른 봉우리를 포함하지 않은 봉우리 => 가장 안쪽 봉우리
		int cnt2 = 0;
		close = Integer.MIN_VALUE;
		for (int i = 0; i < list.size(); i++) {
			int[] current = list.get(i);

			if (close < current[0]) {
				cnt2++;
				close = current[1];
			}
		}

		System.out.println(cnt1 + " " + cnt2);
	}

}
