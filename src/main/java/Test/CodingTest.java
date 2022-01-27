package Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodingTest {
	public static void main(String[] args) {
		final int[] progresses1 = {93, 30, 55};
		final int[] speeds1 = {1, 30, 5};
		final int[] progresses2 = {95, 90, 99, 99, 80, 99};
		final int[] speeds2 = {1, 1, 1, 1, 1, 1};

		Solution solution = new Solution();
		solution.solution(progresses1, speeds1);

		LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
		System.out.println(now);
		System.out.println(now.minusDays(2));

	}

	static class Solution {
		public Solution() {
		}

		public int[] solution(int[] progresses, int[] speeds) {
			int[] answer = {};
			List<Integer> count = new ArrayList<>();

			for (int i = 0; i < progresses.length; i++) {
				count.add((100 - progresses[i]) / speeds[i]);
				log.info("i = {}, count = {}", i, count);
			}


			return answer;
		}
	}


}
