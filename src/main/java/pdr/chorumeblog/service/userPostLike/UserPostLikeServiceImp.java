package pdr.chorumeblog.service.userPostLike;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.model.UserPostLikeEntity;
import pdr.chorumeblog.repository.UserPostLikeRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserPostLikeServiceImp implements UserPostLikeService {

    private final UserPostLikeRepository userPostLikeRepository;

    @Override
    public void saveLike(UserPostLikeEntity entity) {
        UserPostLikeEntity findEntity = userPostLikeRepository.findByUserAndPost(entity.getUser(), entity.getPost());
        if(findEntity == null){
            userPostLikeRepository.save(entity);
            return;
        }
        userPostLikeRepository.delete(findEntity);
    }
}
