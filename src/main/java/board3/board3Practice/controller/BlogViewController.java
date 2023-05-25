package board3.board3Practice.controller;

import board3.board3Practice.dto.ArticleListViewResponse;
import board3.board3Practice.dto.ArticleResponse;
import board3.board3Practice.dto.ArticleViewResponse;
import board3.board3Practice.dto.UpdateArticleRequest;
import board3.board3Practice.entity.Article;
import board3.board3Practice.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogViewController {
    final private BlogService blogService;

    //  전체 보여주기
    @GetMapping("/articles")
    public String articlesList(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream().map(ArticleListViewResponse::new).toList();
        model.addAttribute("articles", articles);
        return "blog/articleList";
    }

    // 기사 상세 보여주기
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));
        return "blog/article";
    }

    // 등록 / 수정 하기
    @GetMapping("/new-article")
    public String newArticleOrUpdateArticle(
            @RequestParam(required = false) Long id,
            Model model) {

        if(id == null) {
            // 이때는 등록임
            model.addAttribute("article", new ArticleViewResponse());
        }else {
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        // 이때는 수정임
        return "blog/new-article";
    }
}
