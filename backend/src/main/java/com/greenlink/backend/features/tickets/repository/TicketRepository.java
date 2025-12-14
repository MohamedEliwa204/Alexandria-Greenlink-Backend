package com.greenlink.backend.features.tickets.repository;

import com.greenlink.backend.features.tickets.model.Ticket;
import com.greenlink.backend.features.auth.model.User;
import com.greenlink.backend.features.tickets.model.TicketCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUser(User user);

    List<Ticket> findByUserId(Long userId);

    List<Ticket> findByUserIdAndStatus(Long userId, String status);

    List<Ticket> findByUserIdAndCategory(Long userId, TicketCategory category);

    List<Ticket> findByUserIdAndFromPlace(Long userId, String fromPlace);

    List<Ticket> findByUserIdAndToPlace(Long userId, String toPlace);

    List<Ticket> findByUserIdAndFromPlaceContaining(Long userId, String fromPlace);

    List<Ticket> findByUserIdAndToPlaceContaining(Long userId, String toPlace);

    List<Ticket> findByUserIdAndFromPlaceContainingOrUserIdAndToPlaceContaining(Long userId1, String fromPlace, Long userId2, String toPlace);
}
