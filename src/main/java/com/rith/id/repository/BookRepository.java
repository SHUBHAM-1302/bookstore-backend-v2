package com.rith.id.repository;

import com.rith.id.entity.Books;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Books, Long> {

    public List<Books> findAllByStatus(String status);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
    @Query("FROM Books b WHERE b.bookId = :id")
    public Optional<Books> getByIdWithPessimisticLock(@Param("id") Long id);

}
