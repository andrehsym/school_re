package ru.hogwarts.school_re.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Avatar {

    @Id
    @GeneratedValue
    private Long id;
    private long fileSize;

    @Lob
    private byte[] data;

    private String filePath;
    private String mediaType;

    @OneToOne
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return fileSize == avatar.fileSize && id.equals(avatar.id) && Arrays.equals(data, avatar.data) && filePath.equals(avatar.filePath) && mediaType.equals(avatar.mediaType);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, fileSize, filePath, mediaType);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", fileSize=" + fileSize +
                ", data=" + Arrays.toString(data) +
                ", filePath='" + filePath + '\'' +
                ", mediaType='" + mediaType + '\'' +
                '}';
    }
}
