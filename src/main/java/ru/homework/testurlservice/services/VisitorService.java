package ru.homework.testurlservice.services;

import ru.homework.testurlservice.domain.Visitor;

public interface VisitorService {
    void saveVisitor(Visitor visitor);
    Visitor getVisitor(String uuid);
    void deleteVisitor(Visitor visitor);
    Visitor findByShorturl(String shorturl);
}
