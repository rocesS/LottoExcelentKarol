package pl.lotto.numberreceiver;

class InputNumbers {
    LotteryTicket lotteryTicket;
    String validationMessage;

    InputNumbers(LotteryTicket lotteryTicket, String validationMessage) {
        this.lotteryTicket = lotteryTicket;
        this.validationMessage = validationMessage;
    }

    LotteryTicket getLotteryTicket() {
        return lotteryTicket;
    }

    String getValidationMessage() {
        return validationMessage;
    }
}
