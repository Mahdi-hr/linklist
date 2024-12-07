
import java.util.Scanner;
class CustomLinkedList<T extends Comparable<T>> {
    private class Node {
        T value;
        Node next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public CustomLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(T value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public int deleteAll(T value) {
        int count = 0;
        while (head != null && head.value.equals(value)) {
            head = head.next;
            count++;
            size--;
        }
        Node current = head;
        while (current != null && current.next != null) {
            if (current.next.value.equals(value)) {
                current.next = current.next.next;
                count++;
                size--;
            } else {
                current = current.next;
            }
        }
        return count;
    }

    public void removeMin() {
        if (head == null) {
            throw new IllegalStateException("The list is empty");
        }

        Node minNode = head;
        Node prevToMin = null;
        Node current = head;
        Node prev = null;

        while (current != null) {
            if (current.value.compareTo(minNode.value) < 0) {
                minNode = current;
                prevToMin = prev;
            }
            prev = current;
            current = current.next;
        }

        if (prevToMin == null) {
            head = head.next;
        } else {
            prevToMin.next = minNode.next;
        }
        size--;
    }

    public boolean equals(CustomLinkedList<T> other) {
        if (this.size != other.size) {
            return false;
        }
        Node current1 = this.head;
        Node current2 = other.head;
        while (current1 != null) {
            if (!current1.value.equals(current2.value)) {
                return false;
            }
            current1 = current1.next;
            current2 = current2.next;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        String cls = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
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

        System.out.println(cls + "Initial list: " + list);

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
                    System.out.println("Enter numbers to add to the list. Type 'stop' to finish:");
                    while (true) {
                        String inputAdd = cin.nextLine();
                        if (inputAdd.equalsIgnoreCase("stop")) {
                            System.out.println(cls +"New list: " + list);
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
                    System.out.print("Enter the number to delete: ");
                    try {
                        int numberToDelete = Integer.parseInt(cin.nextLine());
                        int removedCount = list.deleteAll(numberToDelete);
                        System.out.println(cls + "After deleteAll(" + numberToDelete + "): " + list + " (Removed: " + removedCount + ")");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                    break;

                case "3":
                    try {
                        list.removeMin();
                        System.out.println(cls + "After removeMin: " + list);
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "4":
                    System.out.println(cls + "Current list: " + list);
                    break;

                case "5":
                    CustomLinkedList<Integer> list2 = new CustomLinkedList<>();
                    System.out.println(cls + "Enter numbers for the second list. Type 'stop' to finish:");
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
                    System.out.println(cls + "List1: " + list);
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
