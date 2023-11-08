package pdr.chorumeblog.service.userPostLike;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pdr.chorumeblog.model.UserPostLikeEntity;
import pdr.chorumeblog.repository.UserPostLikeRepository;

@Service
@AllArgsConstructor
public class UserPostLikeServiceImp implements UserPostLikeService {

    private final UserPostLikeRepository userPostLikeRepository;

    @Override
    public void saveLike(UserPostLikeEntity entity) {
        userPostLikeRepository.save(entity);
    }
}
