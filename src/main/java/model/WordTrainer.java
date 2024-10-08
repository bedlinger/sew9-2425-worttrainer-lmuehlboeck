package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a word trainer.
 * @author Leo Mühlböck, Benjamin Edlinger
 * @version 2024-09-30
 */
@XmlRootElement(name = "wordTrainer")
@XmlType(propOrder = {"entries", "selected", "numAsked", "numRight"})
public class WordTrainer {
    private List<WordEntry> entries;
    private int selected;
    private int numAsked;
    private int numRight;

    /**
     * Default entries for the word trainer.
     */
    public static final List<WordEntry> DEFAULT_ENTRIES = Arrays.asList(
            new WordEntry("Elefant", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Elephant_near_ndutu.jpg/640px-Elephant_near_ndutu.jpg"),
            new WordEntry("Hund", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_H%C3%BCndin_2.jpg"),
            new WordEntry("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/Cat_March_2010-1.jpg/640px-Cat_March_2010-1.jpg"),
            new WordEntry("Esel", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Donkey_in_Clovelly%2C_North_Devon%2C_England.jpg/300px-Donkey_in_Clovelly%2C_North_Devon%2C_England.jpg"),
            new WordEntry("Ratte", "https://upload.wikimedia.org/wikipedia/commons/1/15/WildRat.jpg")
    );

    /**
     * Creates a new word trainer with the default entries.
     */
    public WordTrainer() {
        this(DEFAULT_ENTRIES, -1, 0, 0);
    }

    /**
     * Creates a new word trainer with the given entries.
     * @param entries the entries
     * @param selected the selected entry
     * @param numAsked the number of asked entries
     * @param numRight the number of right answers
     */
    public WordTrainer(List<WordEntry> entries, int selected, int numAsked, int numRight) {
        setEntries(entries);
        selectEntry(selected);
        setNumAsked(numAsked);
        setNumRight(numRight);
    }

    /**
     * Returns the entries.
     * @return the entries
     */
    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    public List<WordEntry> getEntries() {
        return entries;
    }

    /**
     * Sets the entries.
     * @param entries the entries
     */
    public void setEntries(List<WordEntry> entries) {
        if(entries == null) throw new IllegalArgumentException();
        this.entries = new ArrayList<>(entries);
    }

    /**
     * Returns the number of asked entries.
     * @return the number of asked entries
     */
    @XmlElement(name = "numAsked")
    public int getNumAsked() {
        return numAsked;
    }

    /**
     * Sets the number of asked entries.
     * @param numAsked the number of asked entries
     */
    public void setNumAsked(int numAsked) {
        if(numAsked < 0) throw  new IllegalArgumentException();
        this.numAsked = numAsked;
    }

    /**
     * Returns the number of right answers.
     * @return the number of right answers
     */
    @XmlElement(name = "numRight")
    public int getNumRight() {
        return numRight;
    }

    /**
     * Sets the number of right answers.
     * @param numRight the number of right answers
     */
    public void setNumRight(int numRight) {
        if(numRight < 0) throw new IllegalArgumentException();
        this.numRight = numRight;
    }

    /**
     * Returns the selected entry.
     * @return the selected entry
     */
    public WordEntry selectRandomEntry() {
        this.selected = (int)(Math.random() * entries.size());
        return getSelectedEntry();
    }

    /**
     * Returns the selected entry.
     * @param index the index of the entry
     * @return the selected entry
     */
    public WordEntry selectEntry(int index) {
        if(index >= entries.size()) throw new IllegalArgumentException();
        this.selected = index;
        return getSelectedEntry();
    }

    /**
     * Returns the selected entry.
     * @return the selected entry
     */
    public WordEntry getSelectedEntry() {
        if(this.selected < 0) return null;
        return this.entries.get(this.selected);
    }

    /**
     * Sets the index of the selected entry.
     * @param selected the index of the selected entry
     */
    public void setSelected(int selected) {
        if (selected < 0 || selected >= entries.size()) {
            throw new IllegalArgumentException();
        }
        this.selected = selected;
    }

    /**
     * Returns the index of the selected entry.
     * @return the index of the selected entry
     */
    @XmlElement(name = "selected")
    public int getSelected() {
        return selected;
    }

    /**
     * Checks if the given input is correct.
     * @param input the input
     * @return true if the input is correct, false otherwise
     */
    public boolean check(String input) {
        boolean correct = getSelectedEntry().getWord().equals(input);
        if(correct) numRight++;
        numAsked++;
        return correct;
    }
}
