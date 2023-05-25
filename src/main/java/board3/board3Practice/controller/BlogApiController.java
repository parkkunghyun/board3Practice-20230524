package board3.board3Practice.controller;

import board3.board3Practice.dto.AddArticleRequest;
import board3.board3Practice.dto.ArticleResponse;
import board3.board3Practice.dto.UpdateArticleRequest;
import board3.board3Practice.entity.Article;
import board3.board3Practice.service.BlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BlogApiController {
    private final BlogService blogService;

    // 데이터를 데이터베이스에 저장!
    @PostMapping("/api/articles")
    public ResponseEntity<Article> saveArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    // 전체 데이터를 조회!
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articleResponses = blogService.findAll().stream().map(ArticleResponse::new).toList();
        return ResponseEntity.ok().body(articleResponses);
    }

    // {id}
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article findArticle = blogService.findById(id);
        log.info("ddd");
        return ResponseEntity.ok().body(new ArticleResponse(findArticle));
    }

    // delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.deleteId(id);
        return ResponseEntity.ok().build();
    }

    // put
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.updateArticle(id, request);

        return ResponseEntity.ok().body(updatedArticle);

    }


}
