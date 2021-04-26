package memoWeb.web.myMemo.service;

import memoWeb.web.main.domain.UserDTO;
import memoWeb.web.main.domain.UserMemoDTO;
import memoWeb.web.myMemo.Repository.MyMemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyMemoService {

    private final MyMemoRepository myMemoRepository;

    @Autowired
    public MyMemoService(MyMemoRepository myMemoRepository) {
        this.myMemoRepository = myMemoRepository;
    }

    public List<UserMemoDTO> getUserMemoListAll(UserDTO user) {
        return myMemoRepository.getUserMemoListAll(user);
    };

    public UserMemoDTO getUserMemoInfo(int idx) {
        return myMemoRepository.getUserMemoInfo(idx);
    }

}
