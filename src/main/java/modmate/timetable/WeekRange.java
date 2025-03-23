package modmate.timetable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class WeekRange {

    private static final int WEEKS_IN_A_SEMESTER = 13;

    private final Optional<LocalDate> startDate;
    private final Optional<LocalDate> endDate;
    private final List<Integer> weeks;

    private WeekRange(Optional<LocalDate> startDate, Optional<LocalDate> endDate, List<Integer> weeks) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.weeks = weeks;
    }

    public WeekRange() {
        this(Optional.empty(), Optional.empty(),
                IntStream.rangeClosed(1, WEEKS_IN_A_SEMESTER)
                        .boxed()
                        .toList());
    }

    public WeekRange(LocalDate startDate, LocalDate endDate) {
        this(Optional.of(startDate), Optional.of(endDate),
                IntStream.rangeClosed(1, WEEKS_IN_A_SEMESTER)
                        .boxed()
                        .toList());
    }

    public WeekRange(LocalDate startDate, LocalDate endDate, List<Integer> weeks) {
        this(Optional.of(startDate), Optional.of(endDate), weeks);
    }

    public WeekRange(LocalDate startDate, LocalDate endDate, int interval) {
        this(Optional.of(startDate), Optional.of(endDate), IntStream.iterate(1,
                week -> week < WEEKS_IN_A_SEMESTER,
                week -> week + interval)
                .boxed()
                .toList());
    }

    public Optional<LocalDate> getStartDate() {
        return startDate;
    }

    public Optional<LocalDate> getEndDate() {
        return endDate;
    }

    public List<Integer> getWeeks() {
        return weeks;
    }

}
