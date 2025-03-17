package modmate;

import modmate.mod.Mod;
import modmate.mod.attribute.ModAttributes;
import modmate.mod.attribute.Faculty;
import modmate.mod.attribute.SemesterAvailability;
import modmate.mod.attribute.WeeklyWorkload;

import java.util.List;

/**
 * A utility class that provides a sample list of mods.
 * This class is used to generate a list of mods with details like mod code, name, description, and prerequisites.
 */
public class SampleMods {

    /**
     * Returns a list of sample mods.
     * <p>
     * Each mod is represented by a {@link Mod} object containing the following details:
     * <ul>
     *     <li>Mod code (e.g., "CS1010")</li>
     *     <li>Mod name (e.g., "Programming Methodology")</li>
     *     <li>Mod description (e.g., "Introduction to programming")</li>
     *     <li>Prerequisites (List of Mod objects)</li>
     * </ul>
     *
     * @return a list of {@link Mod} objects representing sample mods
     */
    public static List<Mod> getMods() {
        // Faculties
        Faculty computingfaculty = new Faculty("School of Computing");
        Faculty businessfaculty = new Faculty("School of Business");

        // Semester availabilities
        List<SemesterAvailability> semesterAvailability1and2 = List.of(
                SemesterAvailability.SEMESTER_1,
                SemesterAvailability.SEMESTER_2);

        List<SemesterAvailability> semesterAvailability1 = List.of(
                SemesterAvailability.SEMESTER_1);

        List<SemesterAvailability> semesterAvailability2 = List.of(
                SemesterAvailability.SEMESTER_2);

        // Workloads
        WeeklyWorkload workload1 = new WeeklyWorkload(3, 6, 9, 12);
        WeeklyWorkload workload2 = new WeeklyWorkload(4, 8, 12, 16);
        WeeklyWorkload workload3 = new WeeklyWorkload(5, 10, 15, 20);

        // Mods
        // Programming Methodology I
        ModAttributes caProgMeth = new ModAttributes(
                computingfaculty,
                semesterAvailability1and2,
                4,
                true,
                null,
                workload1);

        Mod progMeth1 = new Mod(
                "Programming Methodology",
                "CS1010",
                "Introduction to programming",
                caProgMeth);

        // Programming Methodology II
        List<Mod> prqProgMeth1 = List.of(progMeth1);
        ModAttributes caProgMeth2 = new ModAttributes(
                computingfaculty,
                semesterAvailability2,
                4,
                true,
                prqProgMeth1,
                workload1);

        Mod progMeth2 = new Mod(
                "Programming Methodology II",
                "CS2030",
                "Advanced programming",
                caProgMeth2);

        // Software Engineering
        ModAttributes caSoftwareEngineering = new ModAttributes(
                computingfaculty,
                semesterAvailability1,
                4,
                true,
                List.of(),
                workload2);

        Mod softwareEng = new Mod(
                "Software Engineering",
                "CS2103T",
                "Introduction to software engineering",
                caSoftwareEngineering);

        // Computer Networks
        ModAttributes caComputerNetworks = new ModAttributes(
                computingfaculty,
                semesterAvailability1and2,
                4,
                true,
                List.of(),
                workload3);

        Mod computerNetworks = new Mod(
                "Computer Networks",
                "CS2105",
                "Introduction to computer networks",
                caComputerNetworks);

        // Operating Systems
        ModAttributes caOperatingSystems = new ModAttributes(
                computingfaculty,
                semesterAvailability1and2,
                4,
                false,
                List.of(),
                workload2);

        Mod operatingSystems = new Mod(
                "Operating Systems",
                "CS2106",
                "Introduction to operating systems",
                caOperatingSystems);

        // Principles of Accounting
        ModAttributes caPrinciplesOfAccounting = new ModAttributes(
                businessfaculty,
                semesterAvailability1,
                4,
                true,
                List.of(),
                workload2);

        Mod principlesOfAccounting = new Mod(
                "Principles of Accounting",
                "ACC1002",
                "Introduction to accounting",
                caPrinciplesOfAccounting);

        return List.of(
                progMeth1,
                progMeth2,
                softwareEng,
                computerNetworks,
                operatingSystems,
                principlesOfAccounting
        );
    }
}
