package modmate.event;

import java.util.Map;

public class EventFactory {
    private static final Map<String, TriFunction<Period, String, String, Event>> EVENT_MAP = Map.of(
            "Lecture", Lecture::new,
            "Tutorial", Tutorial::new
    );

    public static Event createEvent(String eventType, Period period, String location, String classNo) {
        return EVENT_MAP.getOrDefault(eventType, DefaultEvent::new).apply(period, location, classNo);
    }
}
