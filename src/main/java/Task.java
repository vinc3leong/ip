public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public static String toAlternateCaps(String input) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + toAlternateCaps(description);
        } else {
            return "[ ] " + toAlternateCaps(description);
        }
    }

    public String writeToFile() {
        if (isDone) {
            return "1 | " + description;
        } else {
            return "0 | " + description;
        }
    }
}
