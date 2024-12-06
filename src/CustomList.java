import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomList<T extends Comparable<T>> {
    private List<T> list;

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public CustomList() {
        this.list = new ArrayList<>();
    }

    public void add(T element) {
        list.add(element);
    }

    public int deleteAll(T element) {
        int count = 0;
        while (list.contains(element)) {
            list.remove(element);
            count++;
        }
        return count;
    }

    public void removeMin() {
        if (list.isEmpty()) {
            throw new IllegalStateException("The list is empty");
        }

        T min = list.get(0);
        for (T item : list) {
            if (item.compareTo(min) < 0) {
                min = item;
            }
        }
        list.remove(min);
    }

    public boolean equals(CustomList<T> other) {
        if (this.list.size() != other.list.size()) {
            return false;
        }
        for (int i = 0; i < this.list.size(); i++) {
            if (!this.list.get(i).equals(other.list.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        String cls = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
        CustomList<Integer> list = new CustomList<>();
            Scanner cin = new Scanner(System.in);

            System.out.println("Create the initial list. Enter numbers one by one. Type 'stop' to finish:");

            while (true) {
                String input = cin.nextLine();
                if (input.equalsIgnoreCase("stop")) {
                    break;
                }
                try {
                    int number = Integer.parseInt(input);
                    list.add(number);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number or 'stop' to finish.");
                }
            }

            System.out.println(cls+"Initial list: " + list);

            boolean isRunning = true;

            while (isRunning) {
                System.out.println("\nChoose an operation:");
                System.out.println("1 - Add numbers to the list");
                System.out.println("2 - Delete all occurrences of a number");
                System.out.println("3 - Remove the smallest number");
                System.out.println("4 - Display the list");
                System.out.println("5 - Check equality with another list");
                System.out.println("6 - Exit");

                System.out.print("Enter your choice: ");
                String choice = cin.nextLine();

                switch (choice) {
                    case "1":
                        clearConsole();
                        System.out.println(cls+"Enter numbers to add to the list. Type 'stop' to finish:");
                        while (true) {
                            String inputAdd = cin.nextLine();
                            if (inputAdd.equalsIgnoreCase("stop")) {
                                break;
                            }
                            try {
                                int number = Integer.parseInt(inputAdd);
                                list.add(number);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid number or 'stop' to finish.");
                            }
                        }
                        break;

                    case "2":
                        clearConsole();
                        System.out.print("Enter the number to delete: ");
                        try {
                            int numberToDelete = Integer.parseInt(cin.nextLine());
                            int removedCount = list.deleteAll(numberToDelete);
                            System.out.println(cls+"After deleteAll(" + numberToDelete + "): " + list + " (Removed: " + removedCount + ")");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                        }
                        break;

                    case "3":
                        clearConsole();
                        try {
                            list.removeMin();
                            System.out.println(cls+"After removeMin: " + list);
                        } catch (IllegalStateException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "4":
                        clearConsole();
                        System.out.println(cls+"Current list: " + list);
                        break;

                    case "5":
                        clearConsole();
                        CustomList<Integer> list2 = new CustomList<>();
                        System.out.println(cls+"Enter numbers for the second list. Type 'stop' to finish:");
                        while (true) {
                            String inputEqual = cin.nextLine();
                            if (inputEqual.equalsIgnoreCase("stop")) {
                                break;
                            }
                            try {
                                int number = Integer.parseInt(inputEqual);
                                list2.add(number);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid number or 'stop' to finish.");
                            }
                        }
                        System.out.println(cls+"List1: " + list);
                        System.out.println("List2: " + list2);
                        System.out.println("Are lists equal? " + list.equals(list2));
                        break;

                    case "6": // Exit
                        isRunning = false;
                        System.out.println("Exiting the program. Goodbye!");
                        break;

                    default: // Invalid choice
                        System.out.println("Invalid choice. Please try again.");
                }
            }

            cin.close();
        }
}
