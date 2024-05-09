package junit5_test;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	public Member(String name) {
		Assert.hasText(name, "name must not be empty");
		this.name = name;
	}

	@Override
	public String toString() {
		return "Member{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
