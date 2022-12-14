package study.datajpa.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.member.entity.Member;
import study.datajpa.member.entity.MemberDto;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // public abstract 생략
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findMember(@Param("username") String username, @Param("age") int age);

    @Query("select m from Member m where m.username = :username")
    List<Member> findMember(@Param("username") String username);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new study.datajpa.member.entity.MemberDto(m.id, m.username, t.name) " +
            "from Member m join m.team t")
    List<MemberDto> findMemberDto();


}
