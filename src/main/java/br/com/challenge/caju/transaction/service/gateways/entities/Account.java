package br.com.challenge.caju.transaction.service.gateways.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String accountId;

    @Column(name = "food_balance", nullable = false)
    private BigDecimal foodBalance;

    @Column(name = "meal_balance", nullable = false)
    private BigDecimal mealBalance;

    @Column(name = "cash_balance", nullable = false)
    private BigDecimal cashBalance;

/*    @OneToMany(mappedBy="account")
    private List<TransactionEntity> transactions;*/
}
