package com.greenlink.backend.features.complaint.repository;

import com.greenlink.backend.features.complaint.model.Complaint;
import com.greenlink.backend.features.complaint.model.ComplaintStatus;
import com.greenlink.backend.features.complaint.model.ComplaintType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByUserId(Long userId);

    List<Complaint> findByUserIdAndStatus(Long userId, ComplaintStatus status);

    List<Complaint> findByUserIdAndComplaintType(Long userId, ComplaintType complaintType);

    List<Complaint> findByTicketId(Long ticketId);

    List<Complaint> findByTicketIdAndUserId(Long ticketId, Long userId);

    // Find all complaints (for admin)
    List<Complaint> findByStatus(ComplaintStatus status);
}

