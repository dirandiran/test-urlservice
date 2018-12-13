package ru.homework.testurlservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Visitor {
    public Visitor(){}

    public Visitor(String uuid, String url, String shorturl){
        this.uuid = uuid;
        this.url = url;
        this.shorturl = shorturl;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @JsonProperty("uuid")
    @UniqueElements
    private String uuid;

    @Getter
    @Setter
    @JsonProperty("longurl")
    private String url;

    @Getter
    @Setter
    @JsonProperty("shorturl")
    @UniqueElements
    private String shorturl;
}
