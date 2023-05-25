package board3.board3Practice.service;

import board3.board3Practice.dto.AddArticleRequest;
import board3.board3Practice.dto.ArticleResponse;
import board3.board3Practice.dto.UpdateArticleRequest;
import board3.board3Practice.entity.Article;
import board3.board3Practice.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    // save
    public Article save(AddArticleRequest request) {
        Article article = request.toEntity();
        Article savedArticle = blogRepository.save(article);
        return savedArticle;
    }

    // list
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // {id}
    public Article findById(long id) {
        return blogRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("not found!!"));
    }

    // delete
    public void deleteId(long id) {
        blogRepository.deleteById(id);
    }

    // put
    @Transactional
    public Article updateArticle(long id, UpdateArticleRequest request) {
        Article findArticle = blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found"));

        findArticle.update(request.getTitle(), request.getContent());
        return findArticle;

    }
}
