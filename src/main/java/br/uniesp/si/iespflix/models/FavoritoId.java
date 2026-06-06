package br.uniesp.si.iespflix.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FavoritoId implements Serializable {

    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

    @Column(name = "filme_id", nullable = false)
    private UUID filmeId;
}