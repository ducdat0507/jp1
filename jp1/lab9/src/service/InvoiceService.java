package service;

import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import entity.Account;
import entity.Gender;
import entity.Invoice;
import global.Global;
import igeneric.IContainerService;
import igeneric.ISearchService;

public class InvoiceService implements IContainerService<Invoice>, ISearchService<Invoice> {
    List<Invoice> list;

    public InvoiceService() {
        list = new ArrayList<>();
    }

    @Override
    public void add(Invoice item) {
        list.add(item);
    }

    @Override
    public Invoice get(int id) {
        return list.stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }

    @Override
    public List<Invoice> list(int limit, int offset) {
        return list.stream().limit(limit).skip(offset).toList();
    }

    @Override
    public List<Invoice> list(Comparator<? super Invoice> sort, int limit, int offset) {
        return list.stream().sorted(sort).limit(limit).skip(offset).toList();
    }

    @Override
    public List<Invoice> search(String query) {
        List<Invoice> result = new ArrayList<>();
        try {
            int id = Integer.parseInt(query);
            Optional.ofNullable(get(id)).ifPresent(result::add);
        } catch (NumberFormatException e) { /* Do nothing */ }

        list.stream().filter(Global.fuzzyMatch(query, Invoice::getCustomerName)).forEach(result::add);

        return result;
    }
    
    public List<Account> listAccountsNotInDebt() {
        return list.stream()
            .collect(Collectors.groupingBy(Invoice::getAccount, Collectors.summingDouble(Invoice::getAmountAfterDiscount)))
            .entrySet().stream()
            .collect(Collectors.toMap(x -> x.getKey(), x -> x.getKey().getBalance() - x.getValue()))
            .entrySet().stream()
            .filter(x -> x.getValue() >= 0).map(x -> x.getKey())
            .toList();
    }

    public List<Account> listAccountsInDebt() {
        return list.stream()
            .collect(Collectors.groupingBy(Invoice::getAccount, Collectors.summingDouble(Invoice::getAmount)))
            .entrySet().stream()
            .collect(Collectors.toMap(x -> x.getKey(), x -> x.getKey().getBalance() - x.getValue()))
            .entrySet().stream()
            .filter(x -> x.getValue() < 0).map(x -> x.getKey())
            .toList();
    }

    public List<Invoice> listApplicableWomenDiscount() {
        return list.stream()
            .filter(x -> x.getCustomer().getGender() == Gender.FEMALE && x.getInvoiceTime().getMonth() == Month.AUGUST)
            .toList();
    }
}
