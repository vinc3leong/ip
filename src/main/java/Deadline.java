
public class Deadline extends Task {
    protected String by;
    private MilitaryTime time;

    public Deadline(String description, String by, MilitaryTime time) {
        super(description);
        this.by = by;
        this.time = time;
    }

    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + by + " " + time.toString() + ")";
    }

    @Override
    public String writeToFile() {
        return "D | " + super.writeToFile() + " | " + by + " | " + time.toString();
    }
}
