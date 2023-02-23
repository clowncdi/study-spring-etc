package event_publisher.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberReposiotry extends JpaRepository<Member, Long> {
}
