package memoWeb.web.myMemo.Repository;

import memoWeb.web.main.domain.UserDTO;
import memoWeb.web.main.domain.UserMemoDTO;

import java.util.List;

public interface MyMemoRepository {
    List<UserMemoDTO> getUserMemoListAll(UserDTO user);
    UserMemoDTO getUserMemoInfo(int idx);
}
