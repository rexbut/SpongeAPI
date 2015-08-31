package org.spongepowered.api.event.flow;

import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.Order;

public interface EventFlow<A extends Event> {

    EventFlow<A> order(Order order);

    EventFlow<A> beforeModifications(boolean beforeModifications);

    Flow<A> flow();

}
