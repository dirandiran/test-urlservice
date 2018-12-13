package ru.homework.testurlservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.homework.testurlservice.domain.Visitor;
import ru.homework.testurlservice.services.VisitorService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class ShortUrlController {

    private VisitorService visitorService;

    @Autowired
    public ShortUrlController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping("/")
    public String getRequest(Model model) {
        //System.out.println(jsonIpInString);
        model.addAttribute("longurl", new String());
        return "index";
    }


    @ResponseBody
    @RequestMapping(value = "/generateUrl", method = RequestMethod.POST)
    public Visitor generateShortLink(@RequestBody String jsonVisitorInString, HttpServletRequest request) {
        ObjectMapper mapper = null;
        Visitor visitor = new Visitor();
        try {
            mapper = new ObjectMapper();
            visitor = mapper.readValue(jsonVisitorInString, Visitor.class);
            StringBuilder builder = new StringBuilder(visitor.getUrl());
            builder.append(request.getRemoteAddr());
            byte[] bytes = builder.toString().getBytes("UTF-8");
            UUID uuid = UUID.nameUUIDFromBytes(bytes);
            visitor.setUuid(uuid.toString());
            visitor.setShorturl(Integer.toHexString(uuid.hashCode()));
            visitorService.saveVisitor(visitor);
        } catch (Exception e) {

        }
        return visitor;
    }

    @GetMapping("/link/{shorturl}")
    public String sortChoose(@PathVariable String shorturl) {
        Visitor visitor = visitorService.findByShorturl(shorturl);
        if(visitor.getUrl().contains("https")
                || visitor.getUrl().contains("http")) {
            return "redirect:" + visitor.getUrl();
        }
        return "redirect:https://" + visitor.getUrl();
    }
}
