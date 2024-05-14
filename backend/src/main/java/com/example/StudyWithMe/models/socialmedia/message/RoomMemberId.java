package com.example.StudyWithMe.models.socialmedia.message;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomMemberId implements Serializable {
    @Column(name = "room_id")
    private Long roomId;
    @Column(name = "user_id")
    private Long userId;
}