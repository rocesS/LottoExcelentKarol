package pl.lotto.resultannouncer;

import java.util.List;

record LotteryAnnouncement(String message, List<Integer> yourNumbers, List<Integer> winningNumbers, int hitNumbers) {}
