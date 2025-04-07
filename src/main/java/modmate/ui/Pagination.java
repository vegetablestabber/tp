package modmate.ui;

import java.util.List;
import java.util.Scanner;

public class Pagination<T> {
    private final List<T> items;
    private final int pageSize;
    private int currentPage;

    public Pagination(List<T> items, int pageSize) {
        this.items = items;
        this.pageSize = pageSize;
        this.currentPage = 0;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            clearScreen();
            printPage();
            displayMenu();
            String input = scanner.nextLine().trim().toLowerCase();
            if (!processInput(input, scanner)) {
                break;
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n[Press 'n' for next, 'p' for previous, 'j' to jump to a page, 'q' to quit]");
    }

    private boolean processInput(String input, Scanner scanner) {
        switch (input) {
            case "n":
                goToNextPage();
                break;
            case "p":
                goToPreviousPage();
                break;
            case "j":
                jumpToPage(scanner);
                break;
            case "q":
                return false;
            default:
                System.out.println("Invalid input. Please try again.");
        }
        return true;
    }

    private void goToNextPage() {
        if (currentPage < (items.size() - 1) / pageSize) {
            currentPage++;
        } else {
            System.out.println("You are on the last page.");
        }
    }

    private void goToPreviousPage() {
        if (currentPage > 0) {
            currentPage--;
        } else {
            System.out.println("You are on the first page.");
        }
    }

    private void jumpToPage(Scanner scanner) {
        System.out.print("Enter page number: ");
        String pageInput = scanner.nextLine().trim();
        try {
            int pageNumber = Integer.parseInt(pageInput) - 1;
            if (isValidPageNumber(pageNumber)) {
                currentPage = pageNumber;
            } else {
                System.out.println("Invalid page number. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    private boolean isValidPageNumber(int pageNumber) {
        return pageNumber >= 0 && pageNumber <= (items.size() - 1) / pageSize;
    }

    private void printPage() {
        int start = currentPage * pageSize;
        int end = Math.min(start + pageSize, items.size());
        System.out.println("\nPage " + (currentPage + 1) + " of " + ((items.size() - 1) / pageSize + 1));
        for (int i = start; i < end; i++) {
            System.out.println(items.get(i));
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
