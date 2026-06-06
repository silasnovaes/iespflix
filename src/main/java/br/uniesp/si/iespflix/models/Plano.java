package br.uniesp.si.iespflix.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "plano")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(name = "limite_diario", nullable = false)
    private Short limiteDiario;

    @Column(name = "streams_simultaneos", nullable = false)
    private Short streamsSimultaneos;
}