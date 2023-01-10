package pl.lotto.numberreceiver;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


class TicketRepositoryInMemoryImpl implements TicketRepository {

    private final Map<UUID, LotteryTicket> lotteryTickets = new HashMap<>();

    @Override
    public <S extends LotteryTicket> S insert(S entity) {
        lotteryTickets.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<LotteryTicket> findById(UUID uuid) {
        return Optional.ofNullable(lotteryTickets.get(uuid));
    }

    @Override
    public <S extends LotteryTicket> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends LotteryTicket> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends LotteryTicket> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends LotteryTicket> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends LotteryTicket> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends LotteryTicket> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends LotteryTicket> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends LotteryTicket, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends LotteryTicket> S save(S entity) {
        return null;
    }

    @Override
    public <S extends LotteryTicket> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public List<LotteryTicket> findAll() {
        return null;
    }

    @Override
    public List<LotteryTicket> findAllById(Iterable<UUID> uuids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void delete(LotteryTicket entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends LotteryTicket> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<LotteryTicket> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<LotteryTicket> findAll(Pageable pageable) {
        return null;
    }
}
