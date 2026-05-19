package me.dmdthd2.springdeveloper.repository;

import me.dmdthd2.springdeveloper.dao.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article,Long > {

}
