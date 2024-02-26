package com.kbtg.bootcamp.posttest.service;

import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import com.kbtg.bootcamp.posttest.model.Lottery;
import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LotteryService {
    private final LotteryRepository lotteryRepository;

    public LotteryService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    public List<String> getLotteryNumber() {
        List<Lottery> lotteries = getLotteryAmountThanZeroList();
        return lotteries.stream()
                .map(Lottery::getTicket)
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Lottery> getLotteryAmountThanZeroList() {
        return lotteryRepository.findAllByAmountGreaterThan(0)
                .orElseThrow(() -> new NotFoundException("No lottery found in the system."));
    }

}
