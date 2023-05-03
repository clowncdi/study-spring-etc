package datatime;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.zone.ZoneRules;
import java.time.zone.ZoneRulesException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DateTimeTest {

	@DisplayName("1일 후 구하기")
	@Test
	void shouldGetAfterOneDay() {
		LocalDate theDay = IsoChronology.INSTANCE.date(1582, 10, 4);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		assertThat(theDay.format(formatter)).isEqualTo("1582.10.04");

		LocalDate nextDay = theDay.plusDays(1);
		assertThat(nextDay.format(formatter)).isEqualTo("1582.10.05");
	}

	@DisplayName("1시간 후 구하기")
	@Test
	void shouldGetAfterOneHour() {
		ZoneId seoul = ZoneId.of("Asia/Seoul");
		ZonedDateTime theTime = ZonedDateTime.of(1988, 5, 7, 23, 0, 0, 0, seoul);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
		assertThat(theTime.format(formatter)).isEqualTo("1988.05.07 23:00");
		ZoneRules seoulRules = seoul.getRules();
		assertThat(seoulRules.isDaylightSavings(Instant.from(theTime))).isFalse();

		ZonedDateTime after1Hour = theTime.plusHours(1);
		assertThat(after1Hour.format(formatter)).isEqualTo("1988.05.08 01:00"); // false???
		assertThat(seoulRules.isDaylightSavings(Instant.from(after1Hour))).isTrue();
	}

	@DisplayName("1분 후 구하기")
	@Test
	void shouldGetAfterOneMinute() {
		ZoneId seoul = ZoneId.of("Asia/Seoul");
		ZonedDateTime theTime = ZonedDateTime.of(1961, 8, 9, 23, 59, 59, 0, seoul);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
		assertThat(theTime.format(formatter)).isEqualTo("1961.08.09 23:59");

		ZonedDateTime after1Minute = theTime.plusMinutes(1);
		assertThat(after1Minute.format(formatter)).isEqualTo("1961.08.10 00:30");
	}

	@DisplayName("2초 후 구하기")
	@Test
	void shouldGetAfterTwoSecond() {
		ZoneId utc = ZoneId.of("UTC");
		ZonedDateTime theTime = ZonedDateTime.of(2012, 6, 30, 23, 59, 59, 0, utc);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
		assertThat(theTime.format(formatter)).isEqualTo("2012.06.30 23:59:59");

		ZonedDateTime after2Seconds = theTime.plusSeconds(2);
		assertThat(after2Seconds.format(formatter)).isEqualTo("2012.07.01 00:00:01");
	}

	@DisplayName("1999년 12월 31일을 지정하는 코드")
	@Test
	void shouldGetDate() {
		LocalDate theDay = LocalDate.of(1999, 12, 31);

		assertThat(theDay.getYear()).isEqualTo(1999);
		assertThat(theDay.getMonthValue()).isEqualTo(12);
		assertThat(theDay.getDayOfMonth()).isEqualTo(31);
	}

	@DisplayName("1999년 12월 31일을 지정하는 코드의 실수")
	@Test
	void shouldNotAcceptWrongDate() {
		assertThatThrownBy(() -> LocalDate.of(1999, 13, 31))
				.isInstanceOf(DateTimeException.class);
	}

	@DisplayName("요일 확인하기")
	@Test
	void shouldGetDayOfWeek() {
		LocalDate theDay = LocalDate.of(2014, 1, 1);
		DayOfWeek dayOfWeek = theDay.getDayOfWeek();
		assertThat(dayOfWeek).isEqualTo(DayOfWeek.WEDNESDAY);
	}

	@DisplayName("잘못 지정한 시간대 ID")
	@Test
	void shouldThrowExceptionWhenWrongTimeZoneId() {
		assertThatThrownBy(() -> ZoneId.of("Seoul/Asia"))
			.isInstanceOf(ZoneRulesException.class)
			.hasMessageContaining("Seoul/Asia");
	}

}
