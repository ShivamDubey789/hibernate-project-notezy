package com.notezy;

import java.util.List;
import java.util.Scanner;

public class NotesyApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final NoteDAO noteDAO = new NoteDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n!! N O T E Z Y !!\n");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Update Note");
            System.out.println("4. Delete Note");
            System.out.println("5. Exit\n");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNote();
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    updateNote();
                    break;
                case 4:
                    deleteNote();
                    break;
                case 5:
                    System.out.println("Exiting Notesy. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNote() {
        System.out.print("Enter note title: ");
        String title = scanner.nextLine();
        System.out.print("Enter note description: ");
        String description = scanner.nextLine();

        Note note = new Note();
        note.setTitle(title);
        note.setDescription(description);
        noteDAO.addNote(note);
        System.out.println("Note added successfully!");
    }

    private static void viewNotes() {
        List<Note> notes = noteDAO.getAllNotes();
        System.out.println("\n--- Notes ---");
        for (Note note : notes) {
            System.out.println("ID: " + note.getId());
            System.out.println("Title: " + note.getTitle());
            System.out.println("Description: " + note.getDescription());
            System.out.println("Created At: " + note.getCreatedAt());
            System.out.println("------------------------");
        }
    }

    private static void updateNote() {
        System.out.print("Enter note ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Note note = new Note();
        note.setId(id);

        System.out.print("Enter new title: ");
        note.setTitle(scanner.nextLine());
        System.out.print("Enter new description: ");
        note.setDescription(scanner.nextLine());

        noteDAO.updateNote(note);
        System.out.println("Note updated successfully!");
    }

    private static void deleteNote() {
        System.out.print("Enter note ID to delete: ");
        int id = scanner.nextInt();
        noteDAO.deleteNoteById(id);
        System.out.println("Note deleted successfully!");
    }
}