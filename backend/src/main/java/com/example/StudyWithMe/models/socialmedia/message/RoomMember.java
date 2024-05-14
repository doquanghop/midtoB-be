package com.example.StudyWithMe.models.socialmedia.message;
import com.example.StudyWithMe.models.user.auth.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "room_members")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoomMember {
    @EmbeddedId
    private RoomMemberId roomMemberId;
    @ManyToOne
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    private ChatRoom room;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}