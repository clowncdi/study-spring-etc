package reactor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class FlatMapTest {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student(80, 90, 75),
                new Student(70, 100, 75),
                new Student(85, 90, 85),
                new Student(80, 100, 90)
        );

        students.stream()
                .flatMapToInt(student -> IntStream.of(student.getKor(), student.getEng(), student.getMath()))
                .average()
                .ifPresent(System.out::println);

    }

    private static class Student {

        private int kor;
        private int eng;
        private int math;

        public Student(int kor, int eng, int math) {
            this.kor = kor;
            this.eng = eng;
            this.math = math;
        }

        public int getKor() {
            return kor;
        }

        public int getEng() {
            return eng;
        }

        public int getMath() {
            return math;
        }

    }
}
