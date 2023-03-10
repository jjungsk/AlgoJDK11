package selfStudy.solved.ac.ing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_4485_젤다 {
	
	private static int N; // N = map의 크기
	private static int[][] mapOrigin, mapMin; // mapOrigin = 기본 주어진 map, mapMin = 주어진 map에서 목표지점까지 가는 최단거리를 저장한 map
	private static StringBuilder sb = new StringBuilder();
	private static int[] dr = {-1, 0, 1, 0}; // 북 동 남 서
	private static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int t = 1;
		
		while (N!=0) {
			mapOrigin = new int[N][N];
			mapMin = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					mapOrigin[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			mapMin[0][0] = mapOrigin[0][0]; // mapOrigin 시작 값과 mapMin 시작 값은 같다
			int ans = findMinRoot(mapOrigin, mapMin);
			sb.append("Problem ").append(t++).append(": ").append(ans).append("\n");	
			
			N = Integer.parseInt(br.readLine());
		} // while문 종료
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}
	
	// mapOrigin -> mapMin 가는 최단 루트 갱신 함수
	private static int findMinRoot(int[][] mapOrigin, int[][] mapMin) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[2]-o2[2];
			}
			
		}); // BFS 탐색
		pq.offer(new int[] {0, 0, mapOrigin[0][0]}); // 시작 값 Que에 저장
		
		// Dq 탐색
		while (!pq.isEmpty()) {
			int[] cur = pq.poll(); // 현재 있는 위치
			if (cur[0] == N-1 && cur[1] == N-1) break;
			if (mapMin[cur[0]][cur[1]] < cur[2]) continue;
			
			for (int i = 0; i < 4; i++) { // 4방 탐색
				int nextR = cur[0] + dr[i];
				int nextC = cur[1] + dc[i];
				
				// 경계조건을 벗어 나거나 || 최솟값이 아닌경우 탐색할 필요가 없음
				if (nextR<0 || nextR>=N || nextC<0 || nextC>=N) continue;
				
				if (mapMin[nextR][nextC]!=0 && mapMin[nextR][nextC] <= mapMin[cur[0]][cur[1]]+mapOrigin[nextR][nextC]) continue;
				
				mapMin[nextR][nextC] = mapMin[cur[0]][cur[1]] + mapOrigin[nextR][nextC];
				pq.offer(new int[] {nextR, nextC, mapMin[nextR][nextC]});
				
			}
			
		}
		
		
		return mapMin[N-1][N-1];
	}

}
