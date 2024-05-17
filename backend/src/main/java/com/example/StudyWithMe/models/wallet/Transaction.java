package com.example.StudyWithMe.models.wallet;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "wallet_transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletTransactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;
    @Column(name = " sender_wallet_id")
    private Long senderId;
    @Column(name = "receiver_wallet_id")
    private Long receiverId;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "transaction_time")
    private LocalDateTime transactionTime;
}
