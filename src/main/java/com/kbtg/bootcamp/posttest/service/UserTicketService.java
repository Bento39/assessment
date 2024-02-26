package com.kbtg.bootcamp.posttest.service;

import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import com.kbtg.bootcamp.posttest.response.BuyLotteryResponse;
import com.kbtg.bootcamp.posttest.response.MyLotteryDataResponse;
import com.kbtg.bootcamp.posttest.model.Lottery;
import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.model.UserTicket;
import com.kbtg.bootcamp.posttest.repository.UserTicketRepository;
import com.kbtg.bootcamp.posttest.response.SellBackResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class UserTicketService {
    private final UserTicketRepository userTicketRepository;
    private final LotteryRepository lotteryRepository;

    public UserTicketService(UserTicketRepository userTicketRepository, LotteryRepository lotteryRepository) {
        this.userTicketRepository = userTicketRepository;
        this.lotteryRepository = lotteryRepository;
    }

    @Transactional
    public BuyLotteryResponse createUserTicket(String userId, String ticketId){
        Lottery lottery = lotteryRepository.findFirstByTicket(ticketId)
                .orElseThrow(() -> new NotFoundException("No lottery found in the system."));

        if (lottery.getAmount() <= 0) {
            throw new NotFoundException("Lottery ticket sold out.");
        }

        UserTicket userTicket = new UserTicket();
        userTicket.setUserId(userId);
        userTicket.setTicket(ticketId);
        userTicket.setTicketPrice(lottery.getPrice());
        userTicketRepository.save(userTicket);

        lottery.setAmount(lottery.getAmount() - 1);
        lotteryRepository.save(lottery);
        return new BuyLotteryResponse(String.valueOf(lottery.getId()));
    }

    public MyLotteryDataResponse getMyLotteryDataByUserId(String UserId) {
        List<UserTicket> myUserTicket = getUserTicketList(UserId);
        List<String> myTicketNumber = myUserTicket.stream().map(UserTicket::getTicket).toList();
        List<BigInteger> myTicketPrice = myUserTicket.stream().map(UserTicket::getTicketPrice).toList();
        BigInteger sumMyTicketPrice = myTicketPrice.stream().reduce(BigInteger.ZERO, BigInteger::add);
        long countLottery = myUserTicket.size();
        return new MyLotteryDataResponse(myTicketNumber, sumMyTicketPrice, countLottery);
    }

    public List<UserTicket> getUserTicketList(String UserId) {
        Optional<List<UserTicket>> optionalUserTicketList = userTicketRepository.findByUserId(UserId);
        if(optionalUserTicketList.isEmpty() || optionalUserTicketList.get().isEmpty()){
            throw new NotFoundException("No transaction data found.");
        }
        return optionalUserTicketList.get();
    }

    @Transactional
    public SellBackResponse deleteUserTicket(String userId, String ticketId) {
        Optional<List<UserTicket>> optionalUserTicketList = userTicketRepository.findByUserIdAndTicket(userId, ticketId);
        if(optionalUserTicketList.isEmpty() || optionalUserTicketList.get().isEmpty()){
            throw new NotFoundException("No desired lottery number to sell back found.");
        }

        userTicketRepository.deleteLotteryTicketNative(userId, ticketId);

        Lottery sellBackLottery = lotteryRepository.findFirstByTicket(ticketId)
                .orElseThrow(() -> new NotFoundException("No lottery found in the system."));

        int newAmount = sellBackLottery.getAmount() + 1;
        updateLotteryAmount(ticketId, newAmount);

        return new SellBackResponse(ticketId);
    }

    @Transactional
    public void updateLotteryAmount(String ticketId, int amount) {
        Lottery lotteryToUpdate = lotteryRepository.findFirstByTicket(ticketId)
                .orElseThrow(() -> new NotFoundException("Cannot Update, No lottery found in the system."));

        lotteryToUpdate.setAmount(amount);
        lotteryRepository.save(lotteryToUpdate);
    }
}