package pdr.chorumeblog.service.userPostLike;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pdr.chorumeblog.model.PostEntity;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.model.UserPostLikeEntity;
import pdr.chorumeblog.repository.UserPostLikeRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserPostLikeServiceImp implements UserPostLikeService {

    private final UserPostLikeRepository userPostLikeRepository;

    @Override
    public boolean saveLike(UserPostLikeEntity entity) {
        UserPostLikeEntity findEntity = userPostLikeRepository.findByUserAndPost(entity.getUser(), entity.getPost());
        if(findEntity == null){
            userPostLikeRepository.save(entity);
            return true;
        }
        userPostLikeRepository.delete(findEntity);
        return false;
    }

    @Override
    public boolean checkIfUserLike(PostEntity postEntity) {
        boolean response;
        if(userPostLikeRepository.existsByUserAndPost(postEntity.getUser(), postEntity)){
            response = true;
        } else {
            response = false;
        }
        return response;

    }
}
