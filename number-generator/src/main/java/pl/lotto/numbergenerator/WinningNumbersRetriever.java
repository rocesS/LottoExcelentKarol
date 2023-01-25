package pl.lotto.numbergenerator;

class WinningNumbersRetriever {
    WinningNumbersRepository winningNumbersRepository;
    NumbersGenerator numbersGenerator;


    public WinningNumbersRetriever(WinningNumbersRepository winningNumbersRepo, NumbersGenerator numbersGenerator) {
        this.winningNumbersRepository = winningNumbersRepo;
        this.numbersGenerator = numbersGenerator;
    }

    public WinningNumbers retrieveWonNumbers(String drawDate) {
        WinningNumbers winningNumbers = winningNumbersRepository.findByDrawDate(drawDate);

        if(winningNumbers != null) {
            return winningNumbers;
        }

        return new WinningNumbers(null, null, drawDate);
    }
}
