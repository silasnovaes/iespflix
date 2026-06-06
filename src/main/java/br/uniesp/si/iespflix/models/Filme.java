package br.uniesp.si.iespflix.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "filme")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false, length = 10)
    private String tipo;

    @Column(nullable = false)
    private Short ano;

    @Column(name = "duracao_minutos", nullable = false)
    private Short duracaoMinutos;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal relevancia;

    @Column(columnDefinition = "TEXT")
    private String sinopse;

    @Column(length = 50)
    private String genero;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;
}