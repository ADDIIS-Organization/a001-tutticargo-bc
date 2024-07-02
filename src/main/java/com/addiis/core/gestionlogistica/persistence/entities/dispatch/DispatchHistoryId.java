package com.addiis.core.gestionlogistica.persistence.entities.dispatch;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class DispatchHistoryId implements Serializable {

    @Column(name = "dispatch_states_id")
    private Long dispatchStatesId;

    @Column(name = "dispatches_id")
    private Long dispatchesId;
}