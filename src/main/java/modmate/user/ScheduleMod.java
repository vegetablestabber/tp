package modmate.user;

import modmate.mod.Mod;
import modmate.timetable.Lesson;
import modmate.timetable.Semester;
import modmate.timetable.Timetable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScheduleMod {
    private final Mod mod;
    private final Map<String, ArrayList<Lesson>> selectedLessons;
    private final ArrayList<String> lessonTypes;
    private final Map<String, ArrayList<Lesson>> lessonsForTypes;

    public ScheduleMod(Mod mod) {
        this.mod = mod;
        selectedLessons = new HashMap<>();
        lessonTypes = new ArrayList<>();
        lessonsForTypes = new HashMap<>();

        // TODO: make semester not hardcoded


        if (mod.getTimetables() != null) {

            Timetable timetable = mod
                    .getTimetables()
                    .stream()
                    .filter(
                            t -> t.getSemester().equals(Semester.SEMESTER_2))
                    .findAny().orElse(null);

            // timetable for course exists. start by figuring how many
            // lesson types this course has (eg. lab, tut, tut2, lec)
            if (timetable == null || timetable.getLessons() == null) {
                return;
            }
            timetable
                .getLessons()
                .stream()
                .map(Lesson::getType)
                .forEach(t -> {
                    if (lessonTypes.stream().filter(et -> et.equalsIgnoreCase(t)).findAny().isEmpty()) {
                        lessonTypes.add(t);
                    }
                });

            // for each lesson type, get all the available
            // sessions/slots for that type and sort into hashmap
            for (String type : lessonTypes) {
                ArrayList<Lesson> lessonsForType = new ArrayList<>();
                for (Lesson lesson : timetable.getLessons()) {
                    if (lesson.getType().equalsIgnoreCase(type)) {
                        lessonsForType.add(lesson);
                    }
                }
                lessonsForTypes.put(
                    type,
                    lessonsForType
                );
            }

            // by default, just add any slot for the user
            for (String type : lessonTypes) {
                String id = lessonsForTypes
                    .get(type)
                    .stream().findAny().get().getId();

                selectedLessons.put(
                        type,
                        new ArrayList<>(
                            lessonsForTypes.get(type).stream().filter(
                                    lesson -> lesson.getId().equals(id)
                            ).toList()
                ));
            }
        }
    }

    public Mod getMod() {
        return mod;
    }

    public ArrayList<String> getLessonTypes() {
        return lessonTypes;
    }

    public ArrayList<Lesson> getLessonsForType(String type) {
        return lessonsForTypes.get(type);
    }

    public ArrayList<Lesson> getSelectedLessons(String type) {
        return selectedLessons.get(type);
    }

    public void setLesson(String typeInput, String id) {
        String type =
                getLessonTypes()
                .stream()
                .filter(
                        et -> et.replaceAll("\\s+", "")
                .equalsIgnoreCase(typeInput.replaceAll("\\s+", "")))
                .findAny()
                .orElse(null);
        if (type == null) {
            System.out.println("Lesson type '" + typeInput + "' does not exist.");
            return;
        }

        ArrayList<Lesson> lessonToAdd = new ArrayList<>(
                getLessonsForType(type)
                        .stream()
                        .filter(
                                lesson -> lesson.getId().equals(id))
                        .toList()
        );

        if (lessonToAdd.isEmpty()) {
            System.out.println("Lesson id '" + id + "' does not exist.");
            return;
        }

        selectedLessons.remove(type);
        selectedLessons.put(type, lessonToAdd);
        System.out.println(type + " " + id + " for " + mod.getCode() + " added.");
    }

    public String toString() {
        String result = mod.toString();
        if (!lessonTypes.isEmpty()) {
            result += "\n";
        }
        for (String type : lessonTypes) {
            result += "\n    " + type + ": " + selectedLessons.get(type).toString();
        }
        return result;
    }

}
