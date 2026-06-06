package br.uniesp.si.iespflix.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "metodo_pagamento")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MetodoPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;

    @Column(nullable = false, length = 20)
    private String bandeira;

    @Column(nullable = false, length = 4)
    private String ultimos4;

    @Column(name = "mes_exp", nullable = false)
    private Short mesExp;

    @Column(name = "ano_exp", nullable = false)
    private Short anoExp;

    @Column(name = "nome_portador", nullable = false, length = 150)
    private String nomePortador;

    @Column(name = "token_gateway", nullable = false, length = 120)
    private String tokenGateway;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;
}