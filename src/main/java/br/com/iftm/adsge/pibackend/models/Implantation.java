package br.com.iftm.adsge.pibackend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.Instant;

@Entity
public class Implantation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long description;
    private Instant dtExpectedInitial;
    private Instant dtExpected;
    private Instant dtRealized;
    private Instant dtInitial;
    private String status;


}
