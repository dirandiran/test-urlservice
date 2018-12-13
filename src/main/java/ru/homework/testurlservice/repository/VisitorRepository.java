package ru.homework.testurlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.testurlservice.domain.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    public Visitor findVisitorByUuid(String uuid);
    public Visitor findVisitorByShorturl(String shorturl);
}
