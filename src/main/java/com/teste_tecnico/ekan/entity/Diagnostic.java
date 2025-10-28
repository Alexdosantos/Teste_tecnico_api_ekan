package com.teste_tecnico.ekan.entity;

import java.util.*;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "diagnostic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLRestriction("deleted_at IS NULL")
public class Diagnostic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String note;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    @ManyToOne
    @JoinColumn(name = "cid_id")
    private Cid cid;

}
