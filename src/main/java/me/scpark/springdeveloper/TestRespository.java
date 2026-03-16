package me.scpark.springdeveloper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TestRespository {
    @Repository
    public interface TestRepository extends JpaRepository<Member, Long> {
    }
}
