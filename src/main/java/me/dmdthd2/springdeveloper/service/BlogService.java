package me.dmdthd2.springdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.dmdthd2.springdeveloper.dao.Article;
import me.dmdthd2.springdeveloper.dto.AddArticleRequest;
import me.dmdthd2.springdeveloper.dto.UpdateArticleRequest;
import me.dmdthd2.springdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest articleRequest){
        return blogRepository.save(articleRequest.toEntity());
    }

    public List<Article>findAll(){
        return blogRepository.findAll();
    }
    public Article findById(long id){
        return  blogRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found: "+id));
    }

    public void delete(Long id){
        blogRepository.deleteById(id);
    }

    @Transactional //성공하면 반영 실패하면 취소 더티체킹용
    public Article update(long id, UpdateArticleRequest request){
        Article article= blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found"+ id));
        article.update(request.getTitle(),request.getContent());
        return article;
    }
}
