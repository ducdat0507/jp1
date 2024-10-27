package controller;

import java.util.List;

import global.ANSI;
import igeneric.ISearchController;
import igeneric.ISearchService;

public class SearchController<T> implements ISearchController {
    private ISearchService<T> service;
    public SearchController(ISearchService<T> service) {
        this.service = service;
    }
    
    private long counter = 0;
    public void promptAndSearch() {
        System.out.print("Enter search query: " + ANSI.format(ANSI.FG_LIGHT_YELLOW));
        String query = System.console().readLine();
        System.out.print(ANSI.format(ANSI.CLEAR));
        List<T> result = service.search(query);
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
