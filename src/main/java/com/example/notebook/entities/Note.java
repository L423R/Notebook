package com.example.notebook.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(schema = "notebook",name = "notes")
public class Note {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "message")
    private String message;
    @Column(name = "date_start")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateStart;
    @Column(name = "done")
    private boolean done;
    @Column(name = "date_end")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateEnd;

    public Note() {
    }

    public Note(String message) {
        this.message = message;
        this.dateStart = new Date();
        this.done = false;
    }

    public Note(String message, Date dateStart, Date dateEnd) {
        this.message = message;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.done = false;
    }

    public Note(String message, Date dateStart) {
        this.message = message;
        this.dateStart = dateStart;
        this.done = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id &&
                done == note.done &&
                Objects.equals(message, note.message) &&
                Objects.equals(dateStart, note.dateStart);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, message, dateStart, done);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", dateStart=" + dateStart +
                ", done=" + done +
                '}';
    }
}
