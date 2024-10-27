package controller;

import java.util.Comparator;
import java.util.List;

import global.ANSI;
import igeneric.IContainerService;
import igeneric.IListItemController;
import igeneric.ISearchController;
import igeneric.ISearchService;

public class ListItemController<T> implements IListItemController {
    private IContainerService<T> service;
    private Comparator<? super T> sortCriteria;

    public ListItemController(IContainerService<T> service) {
        this.service = service;
    }
    public ListItemController(IContainerService<T> service, Comparator<? super T> sortCriteria) {
        this.service = service;
        this.sortCriteria = sortCriteria;
    }
    
    private long counter = 0;
    public void list() {
        List<T> result = sortCriteria == null ? service.list(10, 0) : service.list(sortCriteria, 10, 0);
        long length = result.size();
        counter = 0;
        if (length > 0) {
            System.out.println(ANSI.format(ANSI.BOLD, ANSI.UNDERLINE) + "Found " + length + " result(s):" + ANSI.format(ANSI.CLEAR));
            result.forEach(x -> System.out.println(
                String.format("%s% 5d) %s%s", ANSI.format(ANSI.FG_DARK_GRAY), ++counter, ANSI.format(ANSI.CLEAR), x.toString()))
            );
        } else {
            System.out.println(ANSI.format(ANSI.BOLD, ANSI.UNDERLINE, ANSI.FG_LIGHT_RED) + "Found no results" + ANSI.format(ANSI.CLEAR));
        }
    }
}
