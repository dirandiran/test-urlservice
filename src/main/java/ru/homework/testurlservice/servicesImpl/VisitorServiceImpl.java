package ru.homework.testurlservice.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.homework.testurlservice.domain.Visitor;
import ru.homework.testurlservice.repository.VisitorRepository;
import ru.homework.testurlservice.services.VisitorService;

@Service
public class VisitorServiceImpl implements VisitorService {

    private VisitorRepository visitorRepository;

    @Autowired
    public VisitorServiceImpl(VisitorRepository visitorRepository){
        this.visitorRepository = visitorRepository;
    }

    @Override
    public void saveVisitor(Visitor visitor) {
        visitorRepository.save(visitor);
    }

    @Override
    public Visitor getVisitor(String uuid) {
        return visitorRepository.findVisitorByUuid(uuid);
    }

    @Override
    public void deleteVisitor(Visitor visitor) {
        visitorRepository.delete(visitor);
    }

    @Override
    public Visitor findByShorturl(String shorturl) {
        return visitorRepository.findVisitorByShorturl(shorturl);
    }
}
