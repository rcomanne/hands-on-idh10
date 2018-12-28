package sample.web.ui.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public abstract class BaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    public abstract int price();
}
