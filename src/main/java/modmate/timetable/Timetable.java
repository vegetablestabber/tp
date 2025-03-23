package modmate.timetable;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class Timetable {

    private final Semester semester;
    private final Optional<Instant> examInstant;
    private final Optional<Duration> examDuration;
    private final List<Lesson> lessons;

    private Timetable(Semester semester, Optional<Instant> examInstant,
            Optional<Duration> examDuration, List<Lesson> lessons) {
        this.semester = semester;
        this.examInstant = examInstant;
        this.examDuration = examDuration;
        this.lessons = lessons;
    }

    public Timetable(Semester semester, Instant examInstant,
            Duration examDuration, List<Lesson> lessons) {
        this(semester, Optional.of(examInstant), Optional.of(examDuration), lessons);
    }

    public Timetable(Semester semester, List<Lesson> lessons) {
        this(semester, Optional.empty(), Optional.empty(), lessons);
    }

    public Semester getSemester() {
        return semester;
    }

    public Optional<Instant> getExamInstant() {
        return examInstant;
    }

    public Optional<Duration> getExamDuration() {
        return examDuration;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

}
