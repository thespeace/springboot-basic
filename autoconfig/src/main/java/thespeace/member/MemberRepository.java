package thespeace.member;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <h1>JdbcTemplate 을 사용해서 회원을 관리하는 리포지토리</h1>
 * <ul>
 *     <li>DbConfig 에서 JdbcTemplate 을 빈으로 등록했기 때문에 바로 주입받아서 사용할 수 있다.</li>
 *     <li>initTable : 보통 리포지토리에 테이블을 생성하는 스크립트를 두지는 않는다.
 *                     여기서는 예제를 단순화하기 위해 이곳에 사용했다.</li>
 * </ul>
 */
@Repository
public class MemberRepository {

    public final JdbcTemplate template;

    public MemberRepository(JdbcTemplate template) {
        this.template = template;
    }

    public void initTable() {
        template.execute("create table member(member_id varchar primary key, name varchar)");
    }

    public void save(Member member) {
        template.update("insert into member(member_id, name) values (?,?)",
                member.getMemberId(),
                member.getName());
    }

    public Member find(String memberId) {
        return template.queryForObject("select member_id, name from member where member_id = ?",
                BeanPropertyRowMapper.newInstance(Member.class),
                memberId);
    }

    public List<Member> findAll() {
        return template.query("select member_id, name from member",
                BeanPropertyRowMapper.newInstance(Member.class));
    }
}
