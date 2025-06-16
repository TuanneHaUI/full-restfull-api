package vn.tuanne.jobhunter.domain.response.job;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import vn.tuanne.jobhunter.domain.Skill;
import vn.tuanne.jobhunter.util.constant.LevelEnum;

@Getter
@Setter
public class ResCreateJobDTO {
    private long id;

    private String name;
    private String location;
    private double salary;
    private int quantity;
    private LevelEnum level;

    private Instant startDate;
    private Instant endDate;

    private boolean active;
    private Instant createdAt;

    private String createdBy;

    private List<String> skills;
}
