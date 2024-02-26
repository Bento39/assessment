package com.kbtg.bootcamp.posttest.service;


import com.kbtg.bootcamp.posttest.exception.InternalServiceException;
import com.kbtg.bootcamp.posttest.response.AdminResponse;
import com.kbtg.bootcamp.posttest.request.AdminRequest;
import com.kbtg.bootcamp.posttest.model.Lottery;
import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AdminService {
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
    private final LotteryRepository lotteryRepository;

    public AdminService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    @Transactional
    public AdminResponse createLottery(AdminRequest request){
        try {
            Lottery lottery = new Lottery();
            lottery.setTicket(request.ticket());
            lottery.setPrice(request.price());
            lottery.setAmount(request.amount());
            lotteryRepository.save(lottery);
            return new AdminResponse(lottery.getTicket());
        }
        catch (Exception e){
            logger.error("Error creating lottery: {}", e.getMessage(), e);
            throw new InternalServiceException("Error creating lottery");
        }
    }
}
