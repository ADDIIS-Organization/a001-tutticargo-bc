package com.addiis.core.gestionlogistica.persistence.entities.dispatch;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="dispatch_history")
public class DispatchHistory {

    @EmbeddedId
    private DispatchHistoryId id;

    @ManyToOne
    @MapsId("dispatch_states_id")
    @JoinColumn(name = "dispatch_states_id", referencedColumnName = "id")
    private DispatchStatus dispatchState;

    @ManyToOne
    @MapsId("dispatches_id")
    @JoinColumn(name = "dispatches_id", referencedColumnName = "id")
    private Dispatch dispatch;
}
