package collection;

import java.util.Comparator;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member implements Comparator<Member> {
	private int memberId;
	private String memberName;

	public Member() {

	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Member) {
			Member member = (Member)o;
			if (this.memberId == member.memberId) {
				return true;
			}
			else return false;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return memberId;
	}

	@Override
	public String toString() {
		return memberName + " 회원님의 아이디는 " + memberId + "입니다.";
	}

	// @Override
	// public int compareTo(Member member) {
	// 	return (this.memberId - member.memberId)*(-1);
	// }

	@Override
	public int compare(Member member1, Member member2) {
		return (member2.memberId - member1.memberId);
	}
}
