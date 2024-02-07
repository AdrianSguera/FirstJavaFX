package com.ceica.firstjavafx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.SpinnerValueFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Task {
    private String title, description;
    private boolean status;
    private LocalDate creation_time, deadline;

    public Task() {
    }

    public Task(String title, String description, boolean status, LocalDate creation_time, LocalDate deadline) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.creation_time = creation_time;
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(LocalDate creation_time) {
        this.creation_time = creation_time;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
