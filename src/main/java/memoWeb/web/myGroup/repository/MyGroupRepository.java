package memoWeb.web.myGroup.repository;

import memoWeb.web.main.domain.MemberVO;
import memoWeb.web.main.domain.UserScheduleVO;

import java.util.List;

public interface MyGroupRepository {
    List<MemberVO> getUserList (String keyword);
}
