package org.artc.commom.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private LocalDateTime created;
    private LocalDateTime edited;
    private String creator;
    private String editor;
    private Integer deleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getEdited() {
        return edited;
    }

    public void setEdited(LocalDateTime edited) {
        this.edited = edited;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
