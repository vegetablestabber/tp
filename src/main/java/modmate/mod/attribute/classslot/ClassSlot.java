package modmate.mod.attribute.classslot;

import modmate.event.Event;

import java.util.List;

public class ClassSlot {
    private final String classNo;
    private final List<Event> events;

    public ClassSlot(List<Event> events, String classNo) {
        this.events = events;
        this.classNo = classNo;
    }

    public String getClassNo() {
        return classNo;
    }

    @Override
    public String toString() {
        return classNo;
    }
}
