package com.kbtg.bootcamp.posttest.repository;

import com.kbtg.bootcamp.posttest.model.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.List;

@Repository
public interface UserTicketRepository extends JpaRepository<UserTicket,Long> {
    @Modifying
    @Query(value = "DELETE FROM user_ticket WHERE user_id = :userId AND ticket = :ticketId", nativeQuery = true)
   void deleteLotteryTicketNative(@Param("userId")String userId, @Param("ticketId") String ticketId);

    Optional<List<UserTicket>> findByUserId(String userId);

    Optional<List<UserTicket>> findByUserIdAndTicket(String userId, String ticket);


}
